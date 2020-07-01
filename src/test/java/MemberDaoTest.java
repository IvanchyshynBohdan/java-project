import com.library.dao.MemberDao;
import com.library.model.Member;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class MemberDaoTest {
    MemberDao memberDao = new MemberDao();

    @Test
    public void testFindAll() {
        List<Member> memberList = memberDao.findAll();
        assertNotNull("Member list is null", memberList);
        assertEquals(5, memberList.size());
    }

    @Test
    public void testFindById() {
        Member findMember = memberDao.findById(1);
        assertNotNull("Can't find member", findMember);
    }

    @Test
    public void testFindByLastName(){
        Member findMember = memberDao.findByLastName("Ivanchyshyn");
        assertNotNull("Cant find member", findMember);
    }

    @Test
    public void testDeleteById() {
        List<Member> memberList = memberDao.findAll();
        memberDao.delete(1);
        List<Member> memberListAfter = memberDao.findAll();
        Member member = memberDao.findById(1);
        assertNull("Member is not null", member);
        assertNotEquals(memberListAfter.size(), memberList.size());
    }

    @Test
    public void testDelete() {
        List<Member> memberList = memberDao.findAll();
        memberDao.delete(memberDao.findById(2));
        List<Member> memberListAfter = memberDao.findAll();
        assertNull("Member is not null", memberDao.findById(2));
        assertNotEquals(memberListAfter.size(), memberList.size());
    }

    @Test
    public void testUpdate() {
        Member member = memberDao.findById(3);
        member.setCity("Kyiv");
        memberDao.update(member);
        Member memberUpdated = memberDao.findById(member.getId());
        assertEquals(memberUpdated.getCity(), member.getCity());
    }

    @Test
    public void testCreate() {
        Member member = new Member();
        member.setFirstName("Ivan");
        member.setLastName("Petrenko");
        member.setEmail("petrenko@ukr.net");
        member.setPassword("34rt3");
        member.setCity("Lviv");
        member.setPhone("+380977123456");
        memberDao.create(member);
        List<Member> memberList = memberDao.findAll();
        assertNotNull("Member list is null", memberList);
        assertEquals(5, memberList.size());
    }
}
