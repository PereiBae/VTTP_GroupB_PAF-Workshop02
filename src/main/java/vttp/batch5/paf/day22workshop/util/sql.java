package vttp.batch5.paf.day22workshop.util;

public class sql {

    public static final String sql_getAllRsvps = "SELECT * FROM rsvp";
    public static final String sql_getAllRsvpsByName = "SELECT * FROM rsvp WHERE name like ?";
    public static final String sql_insertRsvp = "INSERT INTO rsvp(name, email, phone, confirmation_date, comments) VALUES(?, ?, ?, ?, ?)";
    public static final String sql_updateRsvp = "UPDATE rsvp SET name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? WHERE email = ?";
    public static final String sql_countRsvp = "SELECT COUNT(*) FROM rsvp";

}
