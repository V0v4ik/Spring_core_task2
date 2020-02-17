package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class EventDaoImpl implements EventDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Event getByName(String name) {
        return jdbcTemplate.queryForObject("select * from events where name=?", new Object[]{name}, eventRowMapper);
    }

    @Override
    public Event save(Event event) {
        jdbcTemplate.update("Insert into events (name, base_price, rating) values (?,?,?)",
                event.getName(), event.getBasePrice(), event.getRating());
        return event;
    }

    @Override
    public void remove(Event event) {
        jdbcTemplate.update("Delete from events where name = ?", event.getName());
    }

    @Override
    public Event getById(Long id) {
        return jdbcTemplate.queryForObject("select * from events where id=?", new Object[]{id}, eventRowMapper);
    }

    @Override
    public Collection<Event> getAll() {
        return jdbcTemplate.query("Select * from events", eventRowMapper);
    }

    private RowMapper<Event> eventRowMapper = (rs, i) -> {
        Event event = new Event();
        event.setName(rs.getString("name"));
        event.setBasePrice(rs.getDouble("base_price"));
        event.setRating(EventRating.valueOf(rs.getString("rating")));
        return event;
    };
}
