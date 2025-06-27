package org.yearup.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDao
{
    @Autowired

    List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color);
    List<Product> listByCategoryId(int categoryId);
    Product getById(int productId);
    boolean create(Product product);
    boolean update(int productId, Product product);
    boolean delete(int productId);
}
