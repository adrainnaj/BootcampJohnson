package org.yearup.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yearup.models.Category;

import java.util.List;

@Repository
public interface CategoryDao
{
    @Autowired

    List<Category> getAllCategories();
    Category getById(int categoryId);
    boolean create(Category category);
    boolean update(int categoryId, Category category);
    boolean delete(int categoryId);
}
