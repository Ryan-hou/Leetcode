package com.github.ryan.data_structure.set_map;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className FileOperation
 * @date August 13,2018
 */
public class FileOperation {

    // 读取文件名称为filename的内容，并将其中包含的所有词语放进words中
    public static boolean readFile(String fileName, List<String> words) {

        if (fileName == null || words == null) {
            System.out.println("filename is null or words is null");
            return false;
        }

        Scanner scanner;

        try {

            String parentPath = FileOperation.class.getResource("/").getPath();

            File file = new File(parentPath, "test" + File.separator + fileName);

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println("Cannot open " + fileName);
            return false;
        }

        // 简单分词
        // 这里分词方式相对简陋，没有考虑很多文本处理的特殊问题
        // 只用做demo
        while (scanner.hasNextLine()) {

            String contents = scanner.nextLine().trim();
            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length();) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    i++;
                }
            }
        }


        return true;
    }

    // 寻找字符串s中，从start的位置开始的第一个字母字符的位置
    private static int firstCharacterIndex(String s, int start) {

        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }
}
