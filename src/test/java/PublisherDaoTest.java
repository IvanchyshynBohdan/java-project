import com.library.dao.CategoryDao;
import com.library.dao.PublisherDao;
import com.library.model.Category;
import com.library.model.Publisher;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PublisherDaoTest {
    PublisherDao publisherDao = new PublisherDao();

    @Test
    public void testFindAll() {
        List<Publisher> publisherList = publisherDao.findAll();
        assertNotNull("Publisher list is null", publisherList);
        assertEquals(3, publisherList.size());
    }

    @Test
    public void testFindById() {
        Publisher findPublisher = publisherDao.findById(1);
        assertNotNull("Can't find publisher", findPublisher);
    }

    @Test
    public void testDeleteById() {
        List<Publisher> publisherList = publisherDao.findAll();
        publisherDao.delete(1);
        List<Publisher> publisherListAfter = publisherDao.findAll();
        Publisher publisher = publisherDao.findById(1);
        assertNull("Publisher is not null", publisher);
        assertNotEquals(publisherListAfter.size(), publisherList.size());
    }

    @Test
    public void testDelete() {
        List<Publisher> publisherList = publisherDao.findAll();
        publisherDao.delete(publisherDao.findById(2));
        List<Publisher> publisherListAfter = publisherDao.findAll();
        assertNull("Publisher is not null", publisherDao.findById(2));
        assertNotEquals(publisherListAfter.size(), publisherList.size());
    }

    @Test
    public void testUpdate() {
        Publisher publisher = publisherDao.findById(3);
        publisher.setName("Kolosok");
        publisherDao.update(publisher);
        Publisher publisherUpdated = publisherDao.findById(publisher.getId());
        assertEquals(publisherUpdated.getId(), publisher.getId());
    }

    @Test
    public void testCreate() {
        Publisher publisher = new Publisher();
        publisher.setName("Forum");
        publisher.setSite("https://bookforum.ua/");
        List<Publisher> publisherList = publisherDao.findAll();
        assertNotNull("Publisher list is null", publisher);
        assertEquals(2, publisherList.size());
    }
}
