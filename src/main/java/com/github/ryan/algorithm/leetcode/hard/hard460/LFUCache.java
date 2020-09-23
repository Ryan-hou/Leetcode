package com.github.ryan.algorithm.leetcode.hard.hard460;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {

    private static class Node {
        int key;
        int value;
        int freq;
        Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    // key -> key, value -> Node that contains key, value and frequence of this key
    private Map<Integer, Node> cache;
    // key -> frequency, value -> Node that has this frequency
    // and tail is the recently used Node
    private Map<Integer, LinkedHashSet<Node>> freqMap;
    private final int CAPACITY;
    private int minFreq;

    public LFUCache(int capacity) {
        CAPACITY = capacity;
        minFreq = 1;
        cache = new HashMap<>(capacity * 2);
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        updateVisitedNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (CAPACITY < 1) return;

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            updateVisitedNode(node);
            node.value = value;
        } else {
            Node newNode = new Node(key, value, 1);
            cache.put(key, newNode);
            if (!freqMap.containsKey(1)) {
                freqMap.put(1, new LinkedHashSet<>());
            }
            freqMap.get(1).add(newNode);
            if (cache.size() > CAPACITY) {
                LinkedHashSet<Node> freqNodeList = freqMap.get(minFreq);
                Node removeNode = freqNodeList.iterator().next(); // remove head
                freqNodeList.remove(removeNode);
                cache.remove(removeNode.key);
            }
            minFreq = 1;
        }
    }

    private void updateVisitedNode(Node node) {
        int freq = node.freq;
        node.freq += 1;
        LinkedHashSet<Node> freqNodeList = freqMap.get(freq);
        freqNodeList.remove(node);
        if (freqNodeList.isEmpty() && freq == minFreq) {
            minFreq++;
        }

        if (!freqMap.containsKey(freq + 1)) {
            freqMap.put(freq + 1, new LinkedHashSet<>());
        }
        freqMap.get(freq + 1).add(node);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
