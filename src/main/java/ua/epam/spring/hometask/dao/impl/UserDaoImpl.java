package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from users where email=?", new Object[]{email}, userRowMapper);
    }

    @Override
    public User save(User user) {
        jdbcTemplate.update("Insert into users(first_name, last_name, email, tickets) values (?,?,?,?)",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getTickets().toString());
        return user;
    }

    @Override
    public void remove(User user) {
        jdbcTemplate.update("Delete from users where email=?", user.getEmail());
    }

    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject("select * from users where id=?", new Object[]{id}, userRowMapper);
    }

    @Override
    public Collection<User> getAll() {
        return jdbcTemplate.query("select * from users", userRowMapper);
    }

    private RowMapper<User> userRowMapper = (rs, i) -> {
        User user = new User();
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
//            user.setTickets(new TreeSet<>(rs.getString("tickets").split(", ")));
        return user;
    };
}
