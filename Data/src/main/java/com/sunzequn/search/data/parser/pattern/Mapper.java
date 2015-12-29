package com.sunzequn.search.data.parser.pattern;

import com.sunzequn.search.data.parser.FilePath;
import com.sunzequn.search.data.parser.segment.BM;
import com.sunzequn.search.data.parser.segment.Word;
import com.sunzequn.search.data.utils.ReadUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/12/29.
 */
public class Mapper {

    private static final String ENTITY = "{entity}";
    private List<String> entities = new ArrayList<>();
    private List<Template> templates = new ArrayList<>();
    private ReadUtil readUtil = new ReadUtil(FilePath.template);
    private BM bm = new BM();

    public Mapper() {
        init();
    }

    private void init() {
        List<String> lines = readUtil.readByLine();
        for (String line : lines) {
            String[] paras = StringUtils.split(line, "$");
            String[] words = StringUtils.split(paras[0], " ");
            Template template = new Template(words, paras[1]);
            templates.add(template);
        }
    }

    public String getSparql(String sentence) {
        entities = new ArrayList<>();
        String sparql = findEntity(sentence);
        if (sparql == null) {
            return null;
        }
        String[] sparqls = StringUtils.split(sparql, "*");
        String newSparql = "";
        if (entities.size() == sparqls.length - 1) {
            for (int i = 0; i < sparqls.length - 1; i++) {
                newSparql += sparqls[i];
                newSparql += entities.get(i) + " ";
            }
            newSparql += sparqls[sparqls.length - 1];
            System.out.println(newSparql);
            return newSparql;
        }
        return null;
    }

    private String findEntity(String sentence) {
        System.out.println(sentence);
        Word word = bm.segment(sentence);
        List<String> words = word.getWords();
        System.out.println(words.toString());
        boolean isMap = true;
        for (Template template : templates) {
            String[] templateWords = template.getWords();
            if (words.size() == templateWords.length) {
                isMap = true;
                for (int i = 0; i < words.size(); i++) {
                    if (templateWords[i].equals("{entity}")) {
                        entities.add(words.get(i));
                    } else {
                        if (!words.get(i).equals(templateWords[i])) {
                            entities = new ArrayList<>();
                            isMap = false;
                            break;
                        }
                    }
                }
                if (isMap)
                    return template.getSparql();
            }
        }
        return null;
    }

}
