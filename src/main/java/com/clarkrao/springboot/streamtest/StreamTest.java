package com.clarkrao.springboot.streamtest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    //{https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/}

    /**
     * 流的操作:
     * Intermediate：
     * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
     * peek、 limit、 skip、 parallel、 sequential、 unordered
     *
     * Terminal：
     * forEach、 forEachOrdered、 toArray、 reduce、 collect、
     * min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
     *
     * Short-circuiting：
     * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
     */

    /**
     * 简单求和示例
     */
    public static void easyExample(){
        int[] nums = {1, 2, 3, 4, 6, 5};
      int sum =  Arrays.stream(nums)
                .filter(w -> w % 2 == 0)
                .map(w -> w+1)
//                .forEach(item -> System.out.println("item: "+ item));
                .sum();
        System.out.println("sum : "+sum);
    }

    /**
     * 流的几种构造方法
     */
    public static void flowStructure() {
        //1.Individual values
        Stream<String> stream = Stream.of("a", "b", "c");
        //2.Arrays
        String[] strings = {"a", "b", "c"};
        stream = Stream.of(strings);
        stream = Arrays.stream(strings);
        //3.Collections
        List<String> list = Arrays.asList(strings);
        stream = list.stream();

    }

    /**
     * 需要注意的是，对于基本数值型，目前有三种对应的包装类型 Stream：
     * IntStream、LongStream、DoubleStream
     *
     * 数值流的构造
     */
    public static void numericalStream() {
        IntStream.of(new int[]{1,2,3}).forEach(System.out::println);

        IntStream.range(1,3).forEach(System.out::println);

        IntStream.rangeClosed(1,3).forEach(System.out::println);
    }

    /**
     *  流转换为其它数据结构
     */
    public static void convertToOthers() {
        //一个 Stream 只可以使用一次(此处只是偷懒)
        Stream<String> stream = Stream.of("a", "b", "c");
        // 1. Array
        String[] strings = stream.toArray(String[]::new);
        // 2. Collection
        List<String> list = stream.collect(Collectors.toList());
        ArrayList<String> list1 = stream.collect(Collectors.toCollection(ArrayList::new));
        Set<String> set = stream.collect(Collectors.toSet());
        Stack<String> stack = stream.collect(Collectors.toCollection(Stack::new));
        // 3. String
        String string = stream.collect(Collectors.joining()).toString();
    }

    /**
     * map/flatMap
     */
    public static void mapAndFlatMap(){
        //转换大写
        List<String> wordList = new ArrayList<>();
        wordList.add("abc");
        wordList.add("ddd");
        wordList.add("kkkk");
        List<String> list = wordList.stream()
                            .map(String::toUpperCase)
                            .collect(Collectors.toList());
        list.forEach(System.out::println);
        //平方数
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> integers = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        integers.forEach(System.out::println);
        // 一对多
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        //flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，
        // 最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
        Stream<Integer> outputStream = inputStream
                .flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);
    }

    /**
     * filter
     * filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
     */
    public static void filterTest(){
        //留下偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] integers = Stream.of(sixNums)
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new);
    }

    /**
     * peek 对每个元素执行操作并返回一个新的 Stream
     */
    public static void peekTest(){
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        StreamTest.easyExample();
//        numericalStream();
//        mapAndFlatMap();
        peekTest();
    }
}
