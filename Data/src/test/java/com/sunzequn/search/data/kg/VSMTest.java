package com.sunzequn.search.data.kg;

import com.sunzequn.search.data.kg.fusion.similarity.VSM;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/26.
 */
public class VSMTest {

    public static void main(String[] args) {
        VSM vsm = new VSM();
        String s1 = "影片讲述一个来自台湾的美女创客，根据爷爷的吩咐寻找当年的“乡愁”；一个钟宅本地小伙子，传承阿嬷手艺，创办“一集棒”扁食拌面店，在风景秀丽的五缘湾他们偶然相遇，因为扁食拌面相识，更因为扁食拌面，牵扯出爷爷与阿嬷的一段经年往事。一碗闽南特有的扁食，巧妙串起台海...";
        String s2 = "曾轰动全球且价值高达300亿的钻石太阳之泪惊现中国澳门，某神秘人物委托混迹赌场的盗贼朴澳门（金允石 饰）组织人马侵入赌场金库，盗取钻石，事成之后报酬颇丰。在此之后，朴从韩国网罗了当年被他背叛的伙伴波比（李政宰 饰）、詹姆帕诺（金秀贤 饰）、艾妮可（全智贤 饰）、口香糖（金海淑 饰）、百事（金惠秀 饰），以及中国方面的陈（任达华 饰）、安德鲁（吴达庶 饰）、朱莉（李心洁 饰）、乔尼（曾国祥 饰）等人。这些人中拥有偷盗、绳索、路线、演技等各方面高手，实可谓一次强强联合。 　　在朴澳门的周密策划下，偷盗行动有条不紊地展开。在此过程中两方人马暗暗较劲又相互帮衬配合，紧张有趣而意外迭出，上演了一出中韩盗贼大比拼……";
        System.out.println(vsm.cosine(s1, s1));
    }

}
