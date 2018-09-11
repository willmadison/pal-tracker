package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntries;


    public TimeEntryController(TimeEntryRepository timeEntries) {
        this.timeEntries = timeEntries;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry) {
        return new ResponseEntity<>(timeEntries.create(entry), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        TimeEntry found = timeEntries.find(id);

        if (found != null) {
            return ResponseEntity.ok(found);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Collection<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntries.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long id, @RequestBody TimeEntry toUpdate) {
        TimeEntry updated = timeEntries.update(id, toUpdate);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id) {
        timeEntries.delete(id);
        return ResponseEntity.noContent().build();
    }
}
