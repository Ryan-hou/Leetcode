package com.github.ryan.algorithm.leetcode.easy.easy359;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
@Slf4j
public class Logger {

    private static class Entry {
        String msg;
        int timestamp;
        Entry(String msg, int timestamp) {
            this.msg = msg;
            this.timestamp = timestamp;
        }
    }

    private LinkedList<Entry> q;
    private Set<String> dict;

    /** Initialize your data structure here. */
    public Logger() {
        q = new LinkedList<>();
        dict = new HashSet<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        // clean up
        while (q.size() > 0) {
            Entry head = q.getFirst();
            if (timestamp - head.timestamp >= 10) {
                q.removeFirst();
                dict.remove(head.msg);
            } else {
                break;
            }
        }

        if (dict.contains(message)) {
            return false;
        } else {
            Entry entry = new Entry(message, timestamp);
            q.addLast(entry);
            dict.add(message);
            return true;
        }
    }

}
