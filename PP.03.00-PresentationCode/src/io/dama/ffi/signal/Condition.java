package io.dama.ffi.signal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public interface Condition {
    public void await()                       // analog wait() 
            throws InterruptedException;
    public void awaitUninterruptibly();
    public long awaitNanos(long nanos) 
            throws InterruptedException;
    public boolean await(long time, TimeUnit unit) 
            throws InterruptedException;
    public boolean awaitUntil(Date deadline) 
            throws InterruptedException;

    public void signal();                     // analog notify()
    public void signalAll();                  // analog notifyAll()
}
