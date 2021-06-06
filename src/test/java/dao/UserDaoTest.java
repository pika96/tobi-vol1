package dao;

import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    private UserDao userDao;
    private User userPika;
    private User userMark;
    private User userWeg;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = context.getBean(UserDao.class);
        userDao.deleteAll();

        userPika = new User();
        userPika.setId("1");
        userPika.setName("pika");
        userPika.setPassword("123");

        userMark = new User();
        userMark.setId("2");
        userMark.setName("mark");
        userMark.setPassword("123");

        userWeg = new User();
        userWeg.setId("3");
        userWeg.setName("weg");
        userWeg.setPassword("123");
    }

    @Test
    void notNull() {
        assertThat(userDao).isNotNull();
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        userDao.add(userPika);

        User findById = userDao.get(userPika.getId());
        assertThat(findById).isEqualTo(userPika);
    }

    @Test
    void deleteAll() throws SQLException, ClassNotFoundException {
        userDao.add(userPika);
        userDao.add(userMark);

        assertThat(userDao.getCount()).isEqualTo(2);
    }

    @Test
    void getAll() {
        userDao.add(userPika);
        userDao.add(userMark);
        userDao.add(userWeg);

        List<User> users = userDao.getAll();

        assertThat(users)
                .hasSize(3)
                .containsExactly(userPika, userMark, userWeg);
    }

    @Test
    void getAllWithEmpty() {
        List<User> users = userDao.getAll();

        assertThat(users).hasSize(0);
    }
}
