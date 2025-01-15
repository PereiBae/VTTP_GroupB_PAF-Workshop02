package vttp.batch5.paf.day22workshop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vttp.batch5.paf.day22workshop.model.Rsvp;
import vttp.batch5.paf.day22workshop.model.exception.ResourceNotFoundException;
import vttp.batch5.paf.day22workshop.util.sql;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RsvpRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rsvp> getAllRsvps() {
        List<Rsvp> rsvps = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql.sql_getAllRsvps);
        try {
            while (rowSet.next()) {
                Rsvp rsvp = new Rsvp();
                rsvp.setId(rowSet.getInt("id"));
                rsvp.setName(rowSet.getString("name"));
                rsvp.setEmail(rowSet.getString("email"));
                rsvp.setPhone(rowSet.getString("phone"));
                rsvp.setConfirmationDate(rowSet.getTimestamp("confirmation_date"));
                rsvp.setComments(rowSet.getString("comments"));
                rsvps.add(rsvp);
            }
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("No RSVPs found");
        }
        return rsvps;
    }

    public List<Rsvp> getAllRsvpsByName(String name) {
        List<Rsvp> rsvps = new ArrayList<>();
        String query = sql.sql_getAllRsvpsByName;
        String parameter = "%" + name + "%";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, parameter);

        try {
            while (rowSet.next()) {
                Rsvp rsvp = new Rsvp();
                rsvp.setId(rowSet.getInt("id"));
                rsvp.setName(rowSet.getString("name"));
                rsvp.setEmail(rowSet.getString("email"));
                rsvp.setPhone(rowSet.getString("phone"));
                rsvp.setConfirmationDate(rowSet.getTimestamp("confirmation_date"));
                rsvp.setComments(rowSet.getString("comments"));
                rsvps.add(rsvp);
            }
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("No RSVPs found with name " + name);
        }
        return rsvps;
    }

    public boolean addRsvp(Rsvp rsvp) {
        System.out.println("Confirmation Date: " + rsvp.getConfirmationDate());
        int rsvpCreated = jdbcTemplate.update(sql.sql_insertRsvp,rsvp.getName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments());
        return rsvpCreated > 0;
    }

    public boolean updateRsvp(String email, Rsvp rsvp) {
        int rsvpUpdated = jdbcTemplate.update(sql.sql_updateRsvp,rsvp.getName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(), email);
        return rsvpUpdated > 0;
    }

    public int countRsvps() {
        return jdbcTemplate.queryForObject(sql.sql_countRsvp, Integer.class);
    }

}
