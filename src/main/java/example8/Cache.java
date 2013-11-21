package example8;

import common.Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {
    private final ReentrantReadWriteLock lock =
            new ReentrantReadWriteLock();

    private Map<String, Object> map =
            new HashMap<>();

    public Optional<Object> get(String key) {
        lock.readLock().lock();
        try {
            if (!map.containsKey(key))
                return Optional.absent();
            else
                return Optional.of(map.get(key));
        }
        finally {
            lock.readLock().unlock();
        }
    }

    public void set(String key, Object value) {
        lock.writeLock().lock();
        try {
            map.put(key, value);
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    public boolean hasKey(String key) {
        lock.readLock().lock();
        try {
            return map.containsKey(key);
        }
        finally {
            lock.readLock().unlock();
        }
    }

    public boolean delete(String key) {
        if (!hasKey(key))
            return false;

        else {
            lock.writeLock().lock();
            try {
                if (!map.containsKey(key))
                    return false;

                map.remove(key);
                return true;
            }
            finally {
                lock.writeLock().unlock();
            }
        }
    }
}

