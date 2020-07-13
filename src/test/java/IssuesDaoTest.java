import com.library.dao.BookDao;
import com.library.dao.IssuesDao;
import com.library.model.*;
import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class IssuesDaoTest {
    IssuesDao issuesDao = new IssuesDao();

    @Test
    public void testFindAll() {
        List<Issues> issuesList = issuesDao.findAll();
        assertNotNull("Issue list is null", issuesList);
        assertEquals(4, issuesList.size());
    }

    @Test
    public void testFindById() {
        Issues findIssue = issuesDao.findById(1);
        assertNotNull("Can't find issue", findIssue);
    }

   /* @Test
    public void testFindByTakenDate() {
        Issues findIssue = issuesDao.findByTakenDate(java.sql.Date.valueOf("2020-03-25"));
        assertNotNull("Can't find issue", findIssue);
    }*/

    @Test
    public void testDeleteById() {
        List<Issues> issuesList = issuesDao.findAll();
        issuesDao.delete(1);
        List<Issues> issueListAfter = issuesDao.findAll();
        Issues issues = issuesDao.findById(1);
        assertNull("Issue is not null", issues);
        assertNotEquals(issueListAfter.size(), issuesList.size());
    }

    @Test
    public void testDelete() {
        List<Issues> issuesList = issuesDao.findAll();
        issuesDao.delete(issuesDao.findById(2));
        List<Issues> issueListAfter = issuesDao.findAll();
        assertNull("Issue is not null", issuesDao.findById(2));
        assertNotEquals(issueListAfter.size(), issuesList.size());
    }

    @Test
    public void testUpdate() {
        Issues issues = issuesDao.findById(3);
        issues.setTakenDate(new Date());
        issues.setBroughtDate(new Date());
        issuesDao.update(issues);
        Issues issueUpdated = issuesDao.findById(issues.getId());
        assertEquals(issueUpdated.getId(), issues.getId());
    }

    @Test
    public void testCreate() {
        Issues issues = new Issues();
        Books books = new Books();
        books.setId(5);
        issues.setBooks(books);
        Member member = new Member();
        member.setId(3);
        issues.setMember(member);
        Librarian librarian = new Librarian();
        librarian.setId(2);
        issues.setTakenDate(new Date());
        issues.setBroughtDate(new Date());
        List<Issues> issuesList = issuesDao.findAll();
        assertNotNull("Issue list is null", issues);
        assertEquals(3, issuesList.size());
    }
}
