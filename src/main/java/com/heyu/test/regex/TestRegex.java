package com.heyu.test.regex;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试Java正则表达式
 */
public class TestRegex {

    public static  void main(String[] args){
        /**
         * 仅仅匹配简单字符串
         */
        String str = "abc 124 ewqeq qeqe   qeqe   qeqe  aaaa  fs fsdfs d    sf sf sf  sf sfada dss dee ad a f s f sa a'lfsd;'l";

        //查找三个字符的单词
        Pattern pt = Pattern.compile("\\b\\w{3}\\b");
        Matcher match = pt.matcher(str);
        while (match.find()){
            String group = match.group();
            System.out.println(group);
        }

        // 匹配出邮箱地址
        String str2 = "dadaadad   da da   dasK[PWEOO-123- DASJAD@DHSJK.COM DADA@DAD.CN =0KFPOS9IR23J0IS ADHAJ@565@ADA.COM.CN shuqi@162.com UFSFJSFI-SI- ";
        //边界、一个或多个词字符、@、一个或多个词字符、(.、2-4个词字符)重复一遍或多遍、边界
        Pattern pet2 = Pattern.compile("\\b\\w+@\\w+(\\.\\w{2,4})+\\b");
        Matcher match2 = pet2.matcher(str2);
        while (match2.find()) {
            System.out.println(match2.group());
        }

        /**
         * 匹配规则中包含匹配组，要求匹配得到匹配组的数据
         */
        String sr = "dada ada adad adsda ad asdda adr3 fas daf fas fdsf 234 adda adcsvfe";
        //包含两个匹配组，一个是三个字符，一个是匹配四个字符
        Pattern pet = Pattern.compile("\\b(\\w{3}) *(\\w{4})\\b");
        Matcher match3 = pet.matcher(sr);
        int countAll = match3.groupCount();//2
        while (match3.find()) {
            System.out.print("匹配组结果：");
            for (int i = 0; i < countAll; i++) {
                System.out.print(String.format("\n\t第%s组的结果是:%s",i+1,match3.group(i + 1)));
            }
            System.out.print("\n匹配的整个结果:");
            System.out.println(match3.group());
        }

        /**
         * 将每次得到的结果使用MatcherResult保存
         */
        String sr1 = "dada ada adad adsda ad asdda adr3 fas daf fas fdsf 234 adda";
        Pattern pet1 = Pattern.compile("\\b(\\w{3}) *(\\w{4})\\b");
        Matcher match4 = pet.matcher(sr1);
        MatchResult ms = null;
        while (match4.find()) {
            ms = match4.toMatchResult();
            System.out.print("匹配对象的组结果：");
            for (int i = 0; i < ms.groupCount(); i++) {
                System.out.print(String.format("\n\t第%s组的结果是:%s",i+1,ms.group(i + 1)));
            }
            System.out.print("\n匹配的整个结果:");
            System.out.println(ms.group());
        }
    }
}
