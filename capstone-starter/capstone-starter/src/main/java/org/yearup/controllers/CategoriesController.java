package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
// add annotation to allow cross site origin requests
@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    // add the appropriate annotation for a get action
    @GetMapping
    public ResponseEntity<List<Category>> getAll()
    {
        // find and return all categories
        var categories = categoryDao.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // add the appropriate annotation for a get action
    @GetMapping("/{category_id}")
        // get the category by id
        public ResponseEntity<Category> getById(@PathVariable("category_id") int id){
        Category category = categoryDao.getById(id);

        if(category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    // get a list of product by categoryId
    public ResponseEntity<List<Product>> getProductsById(@PathVariable int categoryId) {
        List<Product> product = productDao.listByCategoryId(categoryId);

        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    // CREATE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
        boolean created = categoryDao.create(category);

        if(created){
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    // UPDATE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping ("/{category_id}")
    public ResponseEntity<Void> updateCategory(@PathVariable("category_id") int id, @RequestBody Category category) {
        Category existing = categoryDao.getById(id);

        if(existing == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setCategoryId(id);
        boolean updated = categoryDao.update(id, category);

        if(updated){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    // delete the category by id
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{category_id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("category_id") int id)
    {
        Category category = categoryDao.getById(id);

        if (category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean successfullyDeleted = categoryDao.delete(id);

        if(successfullyDeleted){
            //204 NO CONTENT
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            //500 THE SERVER IS WRONG
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}