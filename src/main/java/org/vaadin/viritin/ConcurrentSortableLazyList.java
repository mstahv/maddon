package org.vaadin.viritin;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A concurrent version of the {@link SortableLazyList}.
 * <p>
 * This implementation locks calls to {@link #size()}, {@link #findEntities(int)}, {@link #initCacheFormPage(int)}
 * and {@link #get(int)}, enabling access from multiple threads at the same time.
 *
 * @param <T> The type of the objects in the list
 * @author Jesenko Mehmedbasic
 */
public class ConcurrentSortableLazyList<T> extends SortableLazyList<T> {
    private final Lock lock = new ReentrantLock();
    private long lockDelay = 10_000;

    public ConcurrentSortableLazyList(SortableEntityProvider<T> dataProvider) {
        super(dataProvider);
    }

    public ConcurrentSortableLazyList(SortableEntityProvider<T> dataProvider, int pageSize) {
        super(dataProvider, pageSize);
    }

    public ConcurrentSortableLazyList(SortablePagingProvider<T> pageProvider, CountProvider countProvider) {
        super(pageProvider, countProvider);
    }

    public ConcurrentSortableLazyList(SortablePagingProvider<T> pageProvider, CountProvider countProvider, int pageSize) {
        super(pageProvider, countProvider, pageSize);
    }

    public ConcurrentSortableLazyList(MultiSortablePagingProvider<T> pageProvider, CountProvider countProvider, int pageSize) {
        super(pageProvider, countProvider, pageSize);
    }

    /**
     * Sets the amount of time the list waits for a lock.
     *
     * @param time the time in milliseconds.
     */
    public void setLockDelay(long time) {
        if (time < 0) {
            throw new IllegalArgumentException("The time argument must be greater than zero.");
        }
        this.lockDelay = time;
    }

    @Override
    public int size() {
        tryLock();
        try {
            return super.size();
        } finally {
            unlock();
        }
    }

    @Override
    protected List<T> findEntities(int firstRow) {
        tryLock();
        try {
            return super.findEntities(firstRow);
        } finally {
            unlock();
        }
    }

    @Override
    public T get(int index) {
        tryLock();
        try {
            return super.get(index);
        } finally {
            unlock();
        }
    }

    @Override
    protected void initCacheFormPage(int pageIndexForRequest) {
        tryLock();
        try {
            super.initCacheFormPage(pageIndexForRequest);
        } finally {
            unlock();
        }
    }

    private void unlock() {
        lock.unlock();
    }

    private void tryLock() {
        try {
            lock.tryLock(lockDelay, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
