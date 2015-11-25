package com.sunzequn.search.data.utils;

import com.sunzequn.search.data.crawler.exception.ConfigException;

import java.io.*;
import java.util.List;

/**
 * Created by Sloriac on 15/11/25.
 */
public class IOUtil {

    /**
     * The name of the default file.
     */
    private String defaultFile;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public IOUtil() {
        init();
    }

    public void init() {
        defaultFile = PropertiesUtil.getValue("defaultFile");
        try {
            if (defaultFile == null) {
                throw new ConfigException("The value of: defaultFile is not defined in config.");
            } else {
                fileWriter = new FileWriter(defaultFile);
                bufferedWriter = new BufferedWriter(fileWriter);
            }
        } catch (ConfigException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public <T> void write(T t) {
        init();
        try {
            bufferedWriter.write(t.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
