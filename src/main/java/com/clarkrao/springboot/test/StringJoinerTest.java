package com.clarkrao.springboot.test;

import java.util.StringJoiner;

/**
 * @Author: ClarkRao
 * @Date: 2019/2/27 23:07
 * @Description: StringJoinerTest
 */
public class StringJoinerTest {
    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner("Clark");

        joiner.add("Rao");
        joiner.add("SuperBoy");

        System.out.println(joiner.toString());

        StringJoiner sj = new StringJoiner(":", "[", "]");
        System.out.println(sj.add("clark").add("is").add("superMan").toString());
    }
}
