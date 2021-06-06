package dao;

import domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*
     * User 추가
     */
    public void add(final User user) {
        this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }

    /*
     * User 정보 가져오기
     */
    public User get(String id) {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                (rs, rn) -> new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password")
                )
                ,id);
    }

    /*
     * User 삭제
     */
    public void delete(String id) {
        this.jdbcTemplate.update("delete from users where id = ?");
    }

    /*
     * User 정보 초기화
     */
    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }
}
