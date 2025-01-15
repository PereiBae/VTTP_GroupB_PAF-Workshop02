package vttp.batch5.paf.day22workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vttp.batch5.paf.day22workshop.model.Rsvp;
import vttp.batch5.paf.day22workshop.repository.RsvpRepository;

import java.util.List;

@Service
public class RsvpService {

    @Autowired
    private RsvpRepository rsvpRepo;

    public List<Rsvp> getAllRsvps() {
        return rsvpRepo.getAllRsvps();
    }

    public List<Rsvp> getAllRsvpsByName(String name) {
        return rsvpRepo.getAllRsvpsByName(name);
    }

    public boolean addRsvp(Rsvp rsvp) {
        return rsvpRepo.addRsvp(rsvp);
    }

    public boolean updateRsvp(String email, Rsvp rsvp) {
        return rsvpRepo.updateRsvp(email, rsvp);
    }

    public int countRsvps() {
        return rsvpRepo.countRsvps();
    }

}
