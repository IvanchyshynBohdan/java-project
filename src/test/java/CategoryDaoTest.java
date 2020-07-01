import com.library.dao.BookDao;
import com.library.dao.CategoryDao;
import com.library.model.Books;
import com.library.model.Category;
import com.library.model.Publisher;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryDaoTest {
    CategoryDao categoryDao = new CategoryDao();

    @Test
    public void testFindAll() {
        List<Category> categoryList = categoryDao.findAll();
        assertNotNull("Category list is null", categoryList);
        assertEquals(3, categoryList.size());
    }

    @Test
    public void testFindById() {
        Category findCategory = categoryDao.findById(1);
        assertNotNull("Can't find category", findCategory);
    }

    @Test
    public void testDeleteById() {
        List<Category> categoryList = categoryDao.findAll();
        categoryDao.delete(1);
        List<Category> categoryListAfter = categoryDao.findAll();
        Category category = categoryDao.findById(1);
        assertNull("Category is not null", category);
        assertNotEquals(categoryListAfter.size(), categoryList.size());
    }

    @Test
    public void testDelete() {
        List<Category> categoryList = categoryDao.findAll();
        categoryDao.delete(categoryDao.findById(2));
        List<Category> categoryListAfter = categoryDao.findAll();
        assertNull("Category is not null", categoryDao.findById(2));
        assertNotEquals(categoryListAfter.size(), categoryList.size());
    }

    @Test
    public void testUpdate() {
        Category category = categoryDao.findById(3);
        category.setName("Mystery");
        categoryDao.update(category);
        Category categoryUpdated = categoryDao.findById(category.getId());
        assertEquals(categoryUpdated.getId(), category.getId());
    }

    @Test
    public void testCreate() {
        Category category = new Category();
        category.setName("History");
        categoryDao.create(category);
        List<Category> categoryList = categoryDao.findAll();
        assertNotNull("Category list is null", category);
        //assertEquals(2, categoryList.size());
    }
}
