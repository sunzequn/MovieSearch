package com.sunzequn.search.data.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/12/4.
 */
public class ReadUtil {

    private String file;
    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    private ReadUtil() {
    }

    public ReadUtil(String file) {
        this.file = file;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public List<String> readByLine() {
        List<String> strings = new ArrayList<>();
        String string = "";
        try {
            while ((string = bufferedReader.readLine()) != null) {
                strings.add(string.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
