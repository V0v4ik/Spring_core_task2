package ua.epam.spring.hometask.aspect.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.aspect.domain.EventAccessCounter;

@Repository
public class EventAspectDaoImpl implements EventAspectDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventAspectDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public EventAccessCounter getByName(String eventName) {
        return jdbcTemplate.queryForObject("select * from event_access_counter where event_name = ?",
                new Object[]{eventName}, rowMapper);
    }

    @Override
    public void save(EventAccessCounter eventAccessCounter) {
        jdbcTemplate.update("insert into event_access_counter " +
                "(event_name, accessed_by_name, price_queried, tickets_booked) values (?,?,?,?)",
                eventAccessCounter.getEventName(), eventAccessCounter.getAccessByNameCounter(),
                eventAccessCounter.getQueryPriceCounter(), eventAccessCounter.getBookedTicketsCounter());
    }

    @Override
    public void update(EventAccessCounter eventAccessCounter) {
        jdbcTemplate.update("update event_access_counter set " +
                "accessed_by_name=?, price_queried=?, tickets_booked=? where event_name=?",
                eventAccessCounter.getAccessByNameCounter(), eventAccessCounter.getQueryPriceCounter(),
                eventAccessCounter.getBookedTicketsCounter(), eventAccessCounter.getEventName());
    }

    private RowMapper<EventAccessCounter> rowMapper = (rs, i) -> {
        EventAccessCounter eventAccessCounter = new EventAccessCounter();
        eventAccessCounter.setEventName(rs.getString("event_name"));
        eventAccessCounter.setAccessByNameCounter(rs.getLong("accessed_by_name"));
        eventAccessCounter.setQueryPriceCounter(rs.getLong("price_queried"));
        eventAccessCounter.setBookedTicketsCounter(rs.getLong("tickets_booked"));
        return eventAccessCounter;
    };
}
