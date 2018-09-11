package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;

import java.util.Collection;

public interface TimeEntryRepository {
    TimeEntry create(TimeEntry entry);

    TimeEntry find(long id);

    Collection<TimeEntry> list();

    TimeEntry delete(long id);

    TimeEntry update(long id, TimeEntry updated);
}
