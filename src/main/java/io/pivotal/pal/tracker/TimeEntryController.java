package io.pivotal.pal.tracker;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntries;
    private final Counter creationCounter;
    private final Counter readCounter;
    private final Counter listCounter;
    private final Counter updateCounter;
    private final Counter deletionCounter;
    private final AtomicInteger timeEntryCount;

    public TimeEntryController(TimeEntryRepository timeEntries, MeterRegistry registry) {
        this.timeEntries = timeEntries;
        this.creationCounter = registry.counter("TimeEntry.created");
        this.readCounter = registry.counter("TimeEntry.read");
        this.listCounter = registry.counter("TimeEntry.read");
        this.updateCounter = registry.counter("TimeEntry.read");
        this.deletionCounter = registry.counter("TimeEntry.read");
        this.timeEntryCount = registry.gauge("timeEntries.count", new AtomicInteger(0));
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry) {
        TimeEntry created = timeEntries.create(entry);
        creationCounter.increment();
        timeEntryCount.set(timeEntries.list().size());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        TimeEntry found = timeEntries.find(id);

        if (found != null) {
            readCounter.increment();
            return ResponseEntity.ok(found);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Collection<TimeEntry>> list() {
        listCounter.increment();
        return ResponseEntity.ok(timeEntries.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long id, @RequestBody TimeEntry toUpdate) {
        TimeEntry updated = timeEntries.update(id, toUpdate);

        if (updated != null) {
            updateCounter.increment();
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id) {
        timeEntries.delete(id);
        deletionCounter.increment();
        timeEntryCount.set(timeEntries.list().size());
        return ResponseEntity.noContent().build();
    }
}
