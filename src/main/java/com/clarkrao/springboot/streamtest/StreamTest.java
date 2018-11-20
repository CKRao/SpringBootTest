package com.clarkrao.springboot.streamtest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static final int MAGIC_NUMBER = 10000;
    public static final int MAGIC_NUMBER_1 = 5;
    private static StreamTest INSTANCE = null;

    /**
     * 单例模式
     *
     * @return
     */
    public synchronized static StreamTest getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new StreamTest();
        }
        return INSTANCE;
    }

    private StreamTest() {
    }

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
    public static void easyExample() {
        int[] nums = {1, 2, 3, 4, 6, 5};
        int sum = Arrays.stream(nums)
                .filter(w -> w % 2 == 0)
                .map(w -> w + 1)
//                .forEach(item -> System.out.println("item: "+ item));
                .sum();
        System.out.println("sum : " + sum);
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
     * <p>
     * 数值流的构造
     */
    public static void numericalStream() {
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);

        IntStream.range(1, 3).forEach(System.out::println);

        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    /**
     * 流转换为其它数据结构
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
    public static void mapAndFlatMap() {
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
    public static void filterTest() {
        //留下偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] integers = Stream.of(sixNums)
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new);
    }

    /**
     * peek 对每个元素执行操作并返回一个新的 Stream
     */
    public static void peekTest() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    /**
     * Optional 的两个用例
     * <p>
     * 使用 Optional 代码的可读性更好，而且它提供的是编译时检查，
     * 能极大的降低 NPE 这种 Runtime Exception 对程序的影响，
     * 或者迫使程序员更早的在编码阶段处理空值问题，而不是留到运行时再发现和调试。
     */
    public static void opyionalTest() {
        String strA = "abcd";
        String strB = null;
        print("");
        print(strB);
        System.out.println(getLength(strA));
        System.out.println(getLength(""));
        System.out.println(getLength(strB));
    }

    public static int getLength(String str) {
        // Java 8
        return Optional.ofNullable(str).map(String::length).orElse(-1);

        // Pre-Java 8
//        return if(null != str) ? str.length() : -1;
    }

    public static void print(String str) {
        //Java 8
        Optional.ofNullable(str).ifPresent(System.out::println);

        //Pre Java 8
        /*if (null != str) {
            System.out.println(str);
        }*/
    }

    /**
     * reduce 的用例
     * reduce 这个方法的主要作用是把 Stream 元素组合起来。
     */
    public static void reduceTest() {
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值，minValue = -3.0
        Double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = 10, 无起始值
        Integer sum = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sum);
        // 求和，sumValue = 10, 有起始值
        sum = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sum);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F")
                .filter(x -> x.compareTo("Z") > 0)
                .reduce("", String::concat);
        System.out.println(concat);

        /*
         * 上面代码例如第一个示例的 reduce()，第一个参数（空白字符）即为起始值，
         * 第二个参数（String::concat）为 BinaryOperator。
         * 这类有起始值的 reduce() 都返回具体的对象。
         * 而对于第四个示例没有起始值的 reduce()，由于可能没有足够的元素，
         * 返回的是 Optional，请留意这个区别
         */

    }

    /**
     * limit/skip
     * <p>
     * limit 返回 Stream 的前面 n 个元素；
     * skip 则是扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）。
     */
    public void limitAndSkipTest() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            Person person = new Person(i, "name" + i, i);
            persons.add(person);
        }
        List<String> personList = persons.stream()
                .map(Person::getName)
                .limit(10)
                .skip(3)
                .collect(Collectors.toList());

        System.out.println(personList);
    }

    /**
     * 有一种情况是 limit/skip 无法达到 short-circuiting 目的的，
     * 就是把它们放在 Stream 的排序操作后，原因跟 sorted 这个 intermediate 操作有关：
     * 此时系统并不知道 Stream 排序后的次序如何，
     * 所以 sorted 中的操作看上去就像完全没有被 limit 或者 skip 一样。
     * <p>
     * limit 和 skip 对 sorted 后的运行次数无影响
     */
    public void sortedBeforeLimitOrSkip() {
        List<Person> persons = new ArrayList<>();

        for (int i = 1; i < MAGIC_NUMBER_1; i++) {
            Person person = new Person(i, "name" + i, i);
            persons.add(person);
        }

        List<Person> personList = persons.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .limit(2)
                .collect(Collectors.toList());

        System.out.println(personList);
//  输出结果如下：
//        name1
//        name0
//        name2
//        name1
//        name3
//        name2
//        name4
//        name3
//[com.clarkrao.springboot.streamtest.StreamTest$Person@6276ae34, com.clarkrao.springboot.streamtest.StreamTest$Person@7946e1f4]
// 即虽然最后的返回元素数量是 2，但整个管道中的 sorted 表达式执行次数没有像前面例子相应减少。

    }

    /**
     * 对 Stream 的排序通过 sorted 进行，
     * 它比数组的排序更强之处在于你可以首先对 Stream 进行各类
     * map、filter、limit、skip 甚至 distinct 来减少元素数量后，
     * 再排序，这能帮助程序明显缩短执行时间
     * <p>
     * 排序前进行 limit 和 skip
     */
    public void sortedTest() {
//      sortedBeforeLimitOrSkip方法优化
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= MAGIC_NUMBER_1; i++) {
            Person person = new Person(i, "name" + i, i);
            persons.add(person);
        }
        List<Person> personList = persons.stream()
                .limit(2)
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
        System.out.println(personList);
    }

    /**
     * min/max/distinct
     * <p>
     * min 和 max 的功能也可以通过对 Stream 元素先排序，再 findFirst 来实现，
     * 但前者的性能会更好，为 O(n)，而 sorted 的成本是 O(n log n)。
     * 同时它们作为特殊的 reduce 方法被独立出来也是因为求最大最小值是很常见的操作。
     */
    public static void maxAndMinAndDistinct() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("E:\\REDIS_op.txt"));
            int asInt = reader.lines()
                    .mapToInt(String::length)
                    .max()
                    .getAsInt();

            BufferedReader br = new BufferedReader(new FileReader("E:\\REDIS_op.txt"));
            List<String> words = br.lines()
                    .flatMap(line -> Stream.of(line.split(" ")))
                    .filter(word -> word.length() > 0)
                    .map(String::toLowerCase)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            reader.close();
            br.close();
            System.out.println(asInt);
            System.out.println(words);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Stream 有三个 match 方法，从语义上说：
     * <p>
     * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
     * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     * <p>
     * 它们都不是要遍历全部元素才能返回结果。
     * 例如 allMatch 只要一个元素不满足条件，就 skip 剩下的所有元素，返回 false
     */
    public  void matchTest() {
        List<Person> persons = new ArrayList();
        persons.add(new Person(1, "name" + 1, 10));
        persons.add(new Person(2, "name" + 2, 21));
        persons.add(new Person(3, "name" + 3, 34));
        persons.add(new Person(4, "name" + 4, 6));
        persons.add(new Person(5, "name" + 5, 55));

        boolean isAllAdult = persons.stream().allMatch(p -> p.getAge() > 18);

        System.out.println("All are adult?  -> " +isAllAdult);

        boolean isThereAnyChild = persons.stream().anyMatch(p -> p.getAge() < 12);

        System.out.println("Any child? -> " + isThereAnyChild);
    }

    public static void main(String[] args) {
//        StreamTest.easyExample();
//        numericalStream();
//        mapAndFlatMap();
//        peekTest();
//        opyionalTest();
//        reduceTest();

        StreamTest streamTest = StreamTest.getInstance();
//        streamTest.limitAndSkipTest();
//        streamTest.sortedBeforeLimitOrSkip();
//        streamTest.sortedTest();
        streamTest.matchTest();
//        maxAndMinAndDistinct();


    }

    private class Person {
        public int no;
        private String name;
        private int age;

        public Person(int no, String name, int age) {
            this.no = no;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            System.out.println(name);
            return name;
        }
    }
}

