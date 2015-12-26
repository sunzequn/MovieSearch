package com.sunzequn.search.data.kg;

import com.sunzequn.search.data.kg.fusion.similarity.JaccardDis;
import com.sunzequn.search.data.kg.fusion.similarity.JaroWinklerDis;
import com.sunzequn.search.data.kg.fusion.similarity.LevenshteinDis;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/12/23.
 */
public class DisTest {

    @Test
    public void distanceTest() {

//        char[] source = "不知何年，妖界大乱。新妖王对前代势力痛下杀手，更誓要对前妖后腹中的孩子斩尽杀绝。妖后一行躲避追杀来到地处大山深处的永宁村，偏巧遇到捉妖天师霍小岚（白百何 饰）和罗刚（姜武 饰）。一番混乱过后，妖后自知气数将尽，遂将妖蛋放入永宁村保长宋天荫（井柏然 饰）的腹中保存。是夜，小岚所属的天师堂掌门人葛千户（钟汉良 饰）率领手下血洗永宁村，天荫不得已随小岚逃亡。时机成熟，萝卜妖怪胡巴降生人间。在接下来的旅途中，小岚和天荫对彼此的了解不断加深，而胡巴也终于成为他们中间最不可割舍的重要存在。".toCharArray();
//        char[] target = "山雄伟，海辽阔，经奇幻。自古以来人妖共存于世，人欲主天下，妖遂被驱逐入山。其后历朝，妖偶有越界，皆由捉妖天师秘密处理。老妖王辞世，妖界大乱，众妖越界流窜，一时间世间人妖难辨。在偏安深山的永宁村中，村长宋天荫（井柏然 饰）偶遇一路捉妖而来的菜鸟天师霍小岚（白百何 饰），在意外共度一夜后，被妖后托胎，随即生下了小妖王胡巴。为了自己的私心，小岚带着天荫一路同行前往顺天府，保护他躲过...".toCharArray();
//
//        System.out.println("Jaro-Winkler distance: " + JaroWinklerDis.compute(source, target));
//        System.out.println("Levenshtein distance: " + LevenshteinDis.compute(source, target));
//        System.out.println("Jaccard distance: " + JaccardDis.compute(source, target));

        List<String> strings = new ArrayList<>();
        strings.add("孙泽群");
        strings.add("白百何");
        String[] array = strings.toArray(new String[strings.size()]);
        System.out.println(array[0]);

        String[] source = {"白百何", "井柏然", "孙泽群"};
        String[] target = {"白百何", "孙泽群", "井柏然"};

        System.out.println("Jaccard distance: " + JaccardDis.compute(source, target));
    }
}
