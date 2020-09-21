package com.github.ryan.personal.data_structure.tree.bst.tmp;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: FileOps
 * @date February 09,2018
 */
@Slf4j
public class FileOps {

    private FileOps() {}

    public static boolean readFile(String fileName, List<String> word) {

        Pattern pattern = Pattern.compile("[a-zA-Z']+");
        BufferedReader bis = null;
        try {
            String parentPath = FileOps.class.getResource("/").getPath();
            bis = new BufferedReader(new FileReader(new File(parentPath + fileName)));

            String line;
            while ((line = bis.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String s = matcher.group();
                    word.add(s);
                }
            }
            return true;

        } catch (FileNotFoundException e) {
            log.error("{} not found.", fileName, e);
        } catch (IOException e) {
            log.error("{} read exception", fileName, e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("InputStream close exception", e);
                }
            }
        }

        return false;
    }
}
