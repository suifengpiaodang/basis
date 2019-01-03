package com.cyk.basis.stream;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

    //构造流的几种常见方法

    public  void genStream(){
        //String [] strArray = new String[] {"a", "b", "c"};
        // 1. Individual values
        //Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        //stream = Stream.of(strArray);
        //stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        //list.stream().forEach();
    }

    public void toUpper(){
        /*List<String> wordList = Lists.newLinkedList();
        wordList.add("q");
        wordList.add("d");
        wordList.add("f");
        List<String> words = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(words);*/

        /*List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(squareNums);*/


        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        System.out.println(outputStream.collect(Collectors.toList()));

    }


    // Intermediate：
    // map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered

    // Terminal：
    // forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator

    // Short-circuiting：
    // anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit


    public static void main(String[] args) {
        TestStream testStream = new TestStream();
        testStream.toUpper();
    }

}
