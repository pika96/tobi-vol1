import dao.*;
import domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);

        DataSource dataSource = ac.getBean(DataSource.class);

        UserDao userDao = new UserDao(dataSource);

        userDao.deleteAll();

        User user = new User();
        user.setId("pika");
        user.setName("윤지우");
        user.setPassword("pass");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());

        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
