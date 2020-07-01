import com.library.dao.BookDao;
import com.library.model.Books;
import com.library.model.Category;
import com.library.model.Publisher;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao = new BookDao();

    @Test
    public void testFindAll() {
        List<Books> bookList = bookDao.findAll();
        assertNotNull("Book list is null", bookList);
        assertEquals(5, bookList.size());
    }

    @Test
    public void testFindByTitle() {
        Books findBooks = bookDao.findByTitle("The Picture of Dorian Gray");
        assertNotNull("Can't find book", findBooks);
    }

    @Test
    public void testFindById() {
        Books findBooks = bookDao.findById(1);
        assertNotNull("Can't find book", findBooks);
    }

    @Test
    public void testDeleteById() {
        List<Books> booksList = bookDao.findAll();
        bookDao.delete(1);
        List<Books> booksListAfter = bookDao.findAll();
        Books books = bookDao.findById(1);
        assertNull("Book is not null", books);
        assertNotEquals(booksListAfter.size(), booksList.size());
    }

    @Test
    public void testDelete() {
        List<Books> booksList = bookDao.findAll();
        bookDao.delete(bookDao.findById(2));
        List<Books> booksListAfter = bookDao.findAll();
        assertNull("Book is not null", bookDao.findById(2));
        assertNotEquals(booksListAfter.size(), booksList.size());
    }

    @Test
    public void testUpdate() {
        Books books = bookDao.findById(3);
        books.setPrice((float) 17.50);
        bookDao.update(books);
        Books booksUpdated = bookDao.findById(books.getId());
        assertEquals(booksUpdated.getId(), books.getId());
    }

    @Test
    public void testCreate() {
        Books books = new Books();
        books.setTitle("The Very Dark Chicken");
        books.setAuthor("Halyna Vdovychenko");
        Category category = new Category();
        category.setId(2);
        books.setCategory(category);
        Publisher publisher = new Publisher();
        publisher.setId(1);
        books.setPublisher(publisher);
        books.setPrice((float) 60.00);
        bookDao.create(books);
        List<Books> booksList = bookDao.findAll();
        assertNotNull("Book list is null", booksList);
        assertEquals(5, booksList.size());
    }
}
