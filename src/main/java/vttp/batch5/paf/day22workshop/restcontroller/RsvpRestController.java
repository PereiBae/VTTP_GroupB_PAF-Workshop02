package vttp.batch5.paf.day22workshop.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vttp.batch5.paf.day22workshop.model.Rsvp;
import vttp.batch5.paf.day22workshop.service.RsvpService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RsvpRestController {

    @Autowired
    private RsvpService rsvpService;

    @GetMapping("/rsvps")
    public ResponseEntity<List<Rsvp>> getAllRsvps() {
        List<Rsvp> rsvps = rsvpService.getAllRsvps();
        return ResponseEntity.ok().body(rsvps);
    }

    @GetMapping("/rsvp")
    public ResponseEntity<List<Rsvp>> getAllRsvpsByName(@RequestParam("q") String name) {
        List<Rsvp> rsvp = rsvpService.getAllRsvpsByName(name);
        return ResponseEntity.status(201).body(rsvp);
    }

    @PostMapping("/rsvp")
    public ResponseEntity<Rsvp> addRsvp(@RequestBody Rsvp rsvp) {
        boolean rsvpAdded = rsvpService.addRsvp(rsvp);
        if (rsvpAdded) {
            return ResponseEntity.status(201).body(rsvp);
        }
        return ResponseEntity.status(400).body(rsvp);
    }

    @PutMapping("/rsvp/{email}")
    public ResponseEntity<Rsvp> updateRsvp(@PathVariable String email, @RequestBody Rsvp rsvp) {
        boolean rsvpUpdated= rsvpService.updateRsvp(email,rsvp);
        if (rsvpUpdated) {
            return ResponseEntity.status(201).body(rsvp);
        }
        return ResponseEntity.status(404).body(rsvp);
    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<Integer> countRsvps() {
        int rsvpCount = rsvpService.countRsvps();
        return ResponseEntity.status(201).body(rsvpCount);
    }

}
