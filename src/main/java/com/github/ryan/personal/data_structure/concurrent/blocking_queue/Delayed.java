package com.github.ryan.personal.data_structure.concurrent.blocking_queue;

import java.util.concurrent.TimeUnit;

/**
 * A mix-in style interface for making objects that should be
 * acted upon after a given delay.
 *
 * An implementation of this interface must define a
 * compareTo method that provides an ordering consistent
 * with its getDelay method.
 */
public interface Delayed extends Comparable<Delayed> {

    /**
     * Returns the remaining delay associated with this object,
     * in the given time unit.
     *
     * @param unit the time unit
     * @return the remaining delay; zero or negative values indicate
     * that the delay has already elapsed
     */
    long getDelay(TimeUnit unit);
}
