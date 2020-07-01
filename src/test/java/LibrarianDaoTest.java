import com.library.dao.LibrarianDao;
import com.library.model.Librarian;
import com.library.model.Member;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LibrarianDaoTest {
    LibrarianDao librarianDao = new LibrarianDao();

    @Test
    public void testFindAll() {
        List<Librarian> librarianList = librarianDao.findAll();
        assertNotNull("Librarian list is null", librarianList);
        assertEquals(3, librarianList.size());
    }

    @Test
    public void testFindById() {
        Librarian findLibrarian = librarianDao.findById(1);
        assertNotNull("Can't find librarian", findLibrarian);
    }

    @Test
    public void testFindByLastName(){
        Librarian findLibrarian = librarianDao.findByLastName("Shevchuk");
        assertNotNull("Cant find librarian", findLibrarian);
    }

    @Test
    public void testDeleteById() {
        List<Librarian> librarianList = librarianDao.findAll();
        librarianDao.delete(1);
        List<Librarian> librarianListAfter = librarianDao.findAll();
        Librarian librarian = librarianDao.findById(1);
        assertNull("Librarian is not null", librarian);
        assertNotEquals(librarianListAfter.size(), librarianList.size());
    }

    @Test
    public void testDelete() {
        List<Librarian> librarianList = librarianDao.findAll();
        librarianDao.delete(librarianDao.findById(2));
        List<Librarian> librarianListAfter = librarianDao.findAll();
        assertNull("Librarian is not null", librarianDao.findById(2));
        assertNotEquals(librarianListAfter.size(), librarianList.size());
    }

    @Test
    public void testUpdate() {
        Librarian librarian = librarianDao.findById(3);
        librarian.setPhone("+38067585634");
        librarianDao.update(librarian);
        Librarian librarianUpdated = librarianDao.findById(librarian.getId());
        assertEquals(librarianUpdated.getId(), librarian.getId());
    }

    @Test
    public void testCreate() {
        Librarian librarian = new Librarian();
        librarian.setFirstName("Petro");
        librarian.setLastName("Ivanenko");
        librarian.setPhone("+38067122134");
        librarian.setEmail("p_ivanenko@gmail.com");
        librarian.setPassword("er45u");
        librarian.setExperience(2);
        librarianDao.create(librarian);
        List<Librarian> librarianList = librarianDao.findAll();
        assertNotNull("Librarian list is null", librarian);
        assertEquals(3, librarianList.size());
    }
}
