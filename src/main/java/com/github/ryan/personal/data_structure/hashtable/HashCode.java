package com.github.ryan.personal.data_structure.hashtable;

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
