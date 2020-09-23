package com.github.ryan.algorithm.leetcode.hard.hard588;

import java.util.*;
/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
public class FileSystem {

    private static class File {
        boolean isFile = false;
        String content = "";
        // key -> name, value -> File (file or directory)
        Map<String, File> children = new HashMap<>();
    }

    private final File root;

    public FileSystem() {
        root = new File();
    }

    public List<String> ls(String path) {
        File cur = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length; i++) {
            cur = cur.children.get(dirs[i]);
        }

        if (cur.isFile) {
            List<String> list = new ArrayList<>();
            list.add(dirs[dirs.length - 1]);
            return list;
        } else {
            List<String> list = new ArrayList<>(cur.children.keySet());
            Collections.sort(list);
            return list;
        }
    }

    public void mkdir(String path) {
        File cur = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length; i++) {
            Map<String, File> curChildren = cur.children;
            if (!curChildren.containsKey(dirs[i])) {
                curChildren.put(dirs[i], new File());
            }
            cur = curChildren.get(dirs[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        File cur = root;
        String[] dirs = filePath.split("/");
        for (int i = 1; i < dirs.length - 1; i++) {
            cur = cur.children.get(dirs[i]);
        }

        String fileName = dirs[dirs.length - 1];
        if (!cur.children.containsKey(fileName)) {
            File f = new File();
            f.isFile = true;
            cur.children.put(fileName, f);
        }
        cur.children.get(fileName).content += content;
    }

    public String readContentFromFile(String filePath) {
        File cur = root;
        String[] dirs = filePath.split("/");
        for (int i = 1; i < dirs.length; i++) {
            cur = cur.children.get(dirs[i]);
        }
        return cur.content;
    }

}
