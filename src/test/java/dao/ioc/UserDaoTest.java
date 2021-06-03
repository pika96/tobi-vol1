package dao.ioc;

import domain.User;
import org.h2.jdbc.JdbcSQLNonTransientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
public class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        userDao = new DaoFactory().userDao();
        userDao.init();
    }

    @Test
    void addTest() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setId("pika");
        user.setName("윤지우");
        user.setPassword("pass");

        userDao.add(user);

        assertThat(userDao.get(user.getId())).isEqualTo(user);
    }

    @Test
    void getTest() {
        assertThatThrownBy(() -> userDao.get("Empty"))
                .isInstanceOf(JdbcSQLNonTransientException.class);
    }
}
