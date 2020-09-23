package com.github.ryan.algorithm.leetcode.medium.medium355;

import java.util.*;

public class Twitter {

    // key -> userId, value -> User
    private Map<Integer, User> dict;
    private int timestamp;
    private static final int FEED_NUM = 10;

    /** Initialize your data structure here. */
    public Twitter() {
        dict = new HashMap<>();
        timestamp = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!dict.containsKey(userId)) {
            dict.put(userId, new User());
        }
        dict.get(userId).getTweets().add(new Tweet(tweetId, timestamp++));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        // minHeap
        PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> t1.timestamp - t2.timestamp);
        User cur = dict.get(userId);
        if (cur == null) return res;

        for (Tweet t : cur.getTweets()) {
            pq.offer(t);
            if (pq.size() > FEED_NUM) {
                pq.poll();
            }
        }
        for (Integer followeeId : cur.getFollowees()) {
            if (dict.containsKey(followeeId)) {
                for (Tweet t : dict.get(followeeId).getTweets()) {
                    pq.offer(t);
                    if (pq.size() > FEED_NUM) {
                        pq.poll();
                    }
                }
            }
        }

        while (!pq.isEmpty()) {
            res.add(pq.poll().tweetId);
        }
        Collections.reverse(res);
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        if (!dict.containsKey(followerId)) {
            dict.put(followerId, new User());
        }
        dict.get(followerId).getFollowees().add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId || !dict.containsKey(followerId)) return;
        dict.get(followerId).getFollowees().remove(followeeId);
    }

    private static class Tweet {
        int tweetId;
        int timestamp;
        // String content

        Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }

    private static class User {

        List<Tweet> tweets;
        Set<Integer> followees;

        User() {
            this.tweets = new ArrayList<>();
            this.followees = new HashSet<>();
        }

        List<Tweet> getTweets() {
            return this.tweets;
        }

        Set<Integer> getFollowees() {
            return this.followees;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
