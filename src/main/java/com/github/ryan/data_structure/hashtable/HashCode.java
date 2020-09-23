package com.github.ryan.data_structure.hashtable;

import static java.lang.Double.doubleToLongBits;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className HashCode
 * @date August 10,2018
 */
public class HashCode {

    static class Object {

        /**
         * Returns a hashtable code value for the object. This method is
         * supported for the benefit of hashtable tables such as those provided
         * by {@link java.util.HashMap}
         * <p>
         * 1) If two objects are equal according to the {@code equals(Object)}
         * method, then calling the {@code hashCode} method on each of
         * the two objects must produce the same integer result.
         * 2) It is not required that if two objects are unequal
         * according to the {@link #equals(Object)} method, then calling the
         * {@code hashCode} method on each of the two objects must produce
         * distinct integer results. However, the programmer should be aware
         * that producing distinct integer results for unequal objects may improve
         * the performance of hash tables.
         * <p>
         * As much as it reasonably practical, the hashCode method defined by class
         * {@code Object} does return distinct integers for distinct objects. (This is
         * typically implemented by converting the internal address of the object into
         * an integer, but this implementation technique is not required by
         * the Java programming language.)
         *
         * @return a hash code value for this object
         * @see #equals(Object)
         * @see java.lang.System#identityHashCode(java.lang.Object) (Object)
         */
        public native int hashCode();

        public boolean equals(Object obj) {
            return this == obj;
        }

        /**
         * Causes the current thread to wait until either another thread invokes the
         * #notify() method or #notifyAll() method for this object, or a
         * specified amount of time has elapsed.
         *
         * The current thread must own this object's monitor.
         * This method causes the current thread (call it T) to place itself
         * in the wait set for this object and then relinquish any and all
         * synchronization claims on this object. Thread T becomes disabled
         * for thread scheduling purposes and lies dormant until one of four
         * things happens:
         * 1. Some other thread invokes teh #notity method for this object
         * and thread T happens to be arbitrarily chosen as the thread to be
         * awakened.
         * 2. Some other thread invokes the #notifyAll method for this object.
         * 3. Some other thread interrupts thread T
         * 4. The specified amount of real time has elapsed, more or less. If
         * timeout is zero, however, then real time is not taken into consideration
         * and the thread simply waits until notified.
         *
         * The thread T is then removed from the wait set for this object
         * and re-enabled for thread scheduling. It then competes in the usual
         * manner with other threads for the right to synchronized on the object;
         * once it has gained control of the object, all its synchronization claims
         * on the object are restored to the status quo ante - that is, to the situation
         * as of the time that the #wait method was invoked. Thread T then returns
         * from the invocation of the wait method. Thus, on return from the wait
         * method, the synchronization state of the object and of thread T is exactly
         * as it was when the #wait method was invoked.
         *
         * A thread can also wake up without being notified, interrupted, or time out
         * , a so-called spurious wakeup. While this will rarely occur in practice,
         * applications must guard against it by testing for the condition that should
         * have caused the thread to be awakened, and continuing to wait if the
         * condition is not satisfied. In other words, waits should always occur in
         * loops, like this one:
         *
         * synchronized (obj) {
         *     while (<condition does not hold>) {
         *         obj.wait();
         *     }
         *     // Perform action appropriate to condition
         * }
         *
         * If the current thread is interrupted by any other thread before or
         * while it is waiting, then an InterruptedException is thrown. This exception
         * is not thrown until the lock status of this object has been
         * restored as described above.
         *
         * Note that the wait method, as it places the current thread into the
         * wait set for this object, unlocks only this object; any other objects
         * on which the current thread may be synchronized remain locked while this
         * thread waits.
         *
         * This method should only be called by a thread that is the owner
         * of this object's monitor. See the #notify method for a description
         * of the ways in which a thread can become the owner of a monitor.
         *
         * @param timeout the maximum time to wait in milliseconds.
         * @throws IllegalArgumentException if the value of timeout is negative
         * @throws IllegalMonitorStateException if the current thread is not the owner
         *      of the object's monitor
         * @throws InterruptedException if any thread interrupted the current thread
         *      before or while the current thread was waiting for a notification.
         *      The interrupted status of the current thread is cleared when
         *      this exception is thrown.
         * @see #notify()
         * @see #notifyAll()
         */
        // public final native void wait(long timeout) throws InterruptedException;

        /**
         * Wakes up a single thread that is waiting on this object's monitor.
         * If any threads are waiting on this object, one of them is chosen
         * to be awakened. The choice is arbitrary and occurs at
         * the discretion of the implementation. A thread waits on an object's
         * monitor by calling one of the #wait methods.
         *
         * The awakened thread will not be able to proceed until the current
         * thread relinquishes the lock on this object. The awakened thread will
         * compete in the usual manner with any other threads that might be
         * actively competing to synchronize on this object; for example, the awakened
         * thread enjoys no reliable privilege or disadvantage in being
         * the next thread to lock this object.
         *
         * This method should only be called a thread that is the owner of this
         * object's monitor. A thread becomes the owner of the object's monitor
         * in one of the three ways:
         * 1. By executing a synchronized instance method of that object.
         * 2. By executing the body of a synchronized statement that synchronizes on the object
         * 3. For objects of type Class, by executing a synchronized static
         *    method of that class.
         *
         * Only one thread at a time can own an object's monitor.
         *
         * @throws IllegalMonitorStateException if the current thread is not the owner
         *      of this object's monitor.
         * @see #notifyAll()
         * @set #wait
         */
        // public final native void notify();
    }

    static class Integer {

        // The value of the {@code Integer}.
        private final int value;

        public Integer(int value) { this.value = value; }


        @Override
        public int hashCode() {
            return Integer.hashCode(value);
        }

        public static int hashCode(int value) {
            return value;
        }

        @Override
        public boolean equals(java.lang.Object obj) {
            if (obj instanceof Integer) {
                return value == ((java.lang.Integer)obj).intValue();
            }
            return false;
        }
    }

    // Long:
    // public int hashCode() { return Long.hashCode(value); }
    // public static int hashCode(long value) {
    //     return (int)(value ^ (value >>> 32));
    // }

    static class Double {

        public static final double NaN = 0.0d / 0.0;
        // The number of bits used to represent a {@code double} value.
        public static final int SIZE = 64;

        private final double value;

        public Double(double value) {
            this.value = value;
        }

        // public static native double longBitsToDouble(long bits);
        // public static long doubleToLongBits(double value);
        // public static native long doubleToRawLongBits(double value);


        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }

        public static int hashCode(double value) {
            long bits = doubleToLongBits(value);
            return (int) (bits ^ (bits >>> 32));
        }

        @Override
        public boolean equals(java.lang.Object obj) {
            return (obj instanceof Double)
                    && (doubleToLongBits(((Double)obj).value) ==
                        doubleToLongBits(value));
        }
    }

    static class String {

        private final char[] value;
        // Cache the hash code for the string
        private int hash; // Default to 0

        // Note that use of this constructor is
        // unnecessary since Strings are immutable.
        public String() {
            this.value = new char[0];
        }

        @Override
        public int hashCode() {
            int h = hash;
            if (h == 0 && value.length > 0) {
                char[] val = value;

                for (int i = 0; i < value.length; i++) {
                    h = 31 * h + val[i];
                }
                hash = h;
            }
            return h;
        }

        @Override
        public boolean equals(java.lang.Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj instanceof String) {
                String anotherString = (String) obj;
                int n = value.length;
                if (n == anotherString.value.length) {
                    char[] v1 = value;
                    char[] v2 = anotherString.value;
                    int i = 0;
                    while (n-- != 0) {
                        if (v1[i] != v2[i])
                            return false;
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }
    }

    class Student {
        private int age;
        private String firstName;
        private String lastName;

        @Override
        public boolean equals(java.lang.Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Student student = (Student) o;

            if (age != student.age) return false;
            if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
            return lastName != null ? lastName.equals(student.lastName) : student.lastName == null;
        }

        @Override
        public int hashCode() {
            int result = age;
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            return result;
        }
    }

}
