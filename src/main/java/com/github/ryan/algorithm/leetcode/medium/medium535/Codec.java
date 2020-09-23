package com.github.ryan.algorithm.leetcode.medium.medium535;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Codec {
    // random generation
    Map<String, String> s2l = new HashMap<>();
    Map<String, String> l2s = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (l2s.containsKey(longUrl)) {
            return l2s.get(longUrl);
        } else {
            while (true) {
                String shortUrl = generateShortUrl();
                if (!s2l.containsKey(shortUrl)) {
                    l2s.put(longUrl, shortUrl);
                    s2l.put(shortUrl, longUrl);
                    return shortUrl;
                }
            }
        } // end else
    }

    private String generateShortUrl() {
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int n = r.nextInt(62);
            b.append(str.charAt(n));
        }
        return b.toString();
    }

    // Decodes a shortened URL to its original URL.
    // if can't find the shortUrl's long url, return null
    public String decode(String shortUrl) {
        return s2l.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
