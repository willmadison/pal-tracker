package io.pivotal.pal.tracker;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long sequence = 0;

    private Map<Long, TimeEntry> dataStore = new LinkedHashMap<>();

    @Override
    public TimeEntry create(TimeEntry entry) {
        entry.setId(++sequence);
        dataStore.put(entry.getId(), entry);
        return entry;
    }

    @Override
    public TimeEntry find(long id) {
        return dataStore.get(id);
    }

    @Override
    public Collection<TimeEntry> list() {
        return dataStore.values();
    }

    @Override
    public TimeEntry delete(long id) {
        TimeEntry t = find(id);

        if (t != null) {
            dataStore.remove(id);
        }

        return t;
    }

    @Override
    public TimeEntry update(long id, TimeEntry updated) {
        TimeEntry t = find(id);

        if (t != null) {
            updated.setId(id);
            dataStore.put(id, updated);
            return updated;
        }

        return t;
    }
}
