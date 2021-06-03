package dao;

import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        userDao = context.getBean(UserDao.class);
        userDao.deleteAll();
    }

    @Test
    void notNull() {
        assertThat(userDao).isNotNull();
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        User userPika = new User();
        userPika.setId("1");
        userPika.setName("pika");
        userPika.setPassword("123");

        userDao.add(userPika);

        User findById = userDao.get(userPika.getId());
        assertThat(findById).isEqualTo(userPika);
    }

    @Test
    void deleteAll() throws SQLException, ClassNotFoundException {
        User userPika = new User();
        userPika.setId("1");
        userPika.setName("pika");
        userPika.setPassword("123");

        User userMark = new User();
        userMark.setId("2");
        userMark.setName("mark");
        userMark.setPassword("123");

        userDao.add(userPika);
        userDao.add(userMark);

        assertThat(userDao.getCount()).isEqualTo(2);

    }
}
