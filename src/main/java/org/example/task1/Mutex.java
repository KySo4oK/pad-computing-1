package org.example.task1;

import java.util.ConcurrentModificationException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Mutex {

    private final AtomicBoolean flag = new AtomicBoolean(Boolean.FALSE);
    private Thread lockThread;
    private final AtomicBoolean notify = new AtomicBoolean(Boolean.FALSE);
    private final AtomicBoolean notifyAll = new AtomicBoolean(Boolean.FALSE);

    public void lock() {
        boolean currentValue = flag.get();

        while (!flag.compareAndSet(currentValue, Boolean.FALSE)){
            Thread.yield();
            currentValue = flag.get();
        }

        lockThread = Thread.currentThread();
    }

    public void unlock () {
        lockThread = null;
        flag.getAndSet(Boolean.FALSE);
    }

    public void myWait () {
        checkIfInSynchronizedBlock();
        unlock();
        while (notifyAll.get() || notify.compareAndSet(Boolean.TRUE, Boolean.FALSE)) {
            Thread.yield();
        }
        lock();
    }

    public void myNotify() {
        checkIfInSynchronizedBlock();
        notify.set(Boolean.TRUE);
    }

    public void myNotifyAll() {
        checkIfInSynchronizedBlock();
        notifyAll.set(Boolean.TRUE);
    }

    private void checkIfInSynchronizedBlock() {
        Thread currentThread = Thread.currentThread();
        if (currentThread != lockThread) {
            throw new ConcurrentModificationException();
        }
    }

}
