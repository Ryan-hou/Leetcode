package com.github.ryan.algorithm.leetcode.medium.medium1146;

import java.util.HashMap;
import java.util.Map;

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
public class SnapshotArray {

    private int snapId;
    private Snapshot[] snaps;

    public SnapshotArray(int length) {
        snaps = new Snapshot[length];
        for (int i = 0; i < length; i++) {
            snaps[i] = new Snapshot();
        }
        snapId = 0;
    }

    public void set(int index, int val) {
        snaps[index].data.put(snapId, val);
    }

    public int snap() {
        snapId++;
        return snapId - 1;
    }

    public int get(int index, int snap_id) {
        if (snaps[index].data.size() == 0) {
            return 0;
        } else {
            for (int i = snap_id; i >= 0; i--) {
                if (snaps[index].data.get(i) != null) {
                    return snaps[index].data.get(i);
                }
            }
            return 0;
        }
    }

    private static class Snapshot {
        // key -> snapId, value -> value at this snapId
        Map<Integer, Integer> data;
        Snapshot() {
            data = new HashMap<>();
        }
    }

}
