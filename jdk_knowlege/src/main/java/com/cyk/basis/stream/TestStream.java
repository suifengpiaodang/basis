package com.cyk.basis.stream;


import com.google.common.collect.Lists;
import model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
        System.out.println(outputStream.collect(toList()));

    }


    // Intermediate：
    // map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered

    // Terminal：
    // forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator

    // Short-circuiting：
    // anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit


    public void testMap(){
        List<String> alpha = Arrays.asList("a", "b", "c", "d");
        List<String> afterAlpha = alpha.stream().map(String::toUpperCase).collect(toList());
        System.out.println(afterAlpha.toString());

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(toList());
        System.out.println(squareNums);

        List<User> userList = this.genUserList();
        List<String> afterUserList = userList.stream().map(User::getUserName).collect(toList());
        List<String> aftUserList = userList.stream().map((User u)-> u.getUserName()).collect(toList());
        System.out.println(afterUserList);
        System.out.println(aftUserList);

        String[] words = new String[]{"Hello","World"};
        List<String[]> a = Arrays.stream(words).map(word -> word.split("")).distinct().collect(toList());
        System.out.println(a.get(0).toString());
        System.out.println(a.get(1).toString());

        // 对象list 转换为 其他对象list
    }

    public void testFlatMap(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        List<Integer> integerList = inputStream.flatMap((childList) -> childList.stream()).collect(toList());
        System.out.println(integerList);



        String[] words = new String[]{"Hello","World"};
        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        a.forEach(System.out::print);

    }

    public void testFilter(){
        List<User> userList = this.genUserList();
        List<User> afterUserList =  userList.stream().filter((User u)->u.getUserName().length()>5).collect(toList());
        System.out.println(afterUserList);


        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        System.out.println(evens.toString());
    }

    //forEach 消费后不存在
    public void testForEach(){
        List<User> userList = this.genUserList();
        userList.stream().forEach(user -> System.out.println(user.getUserName()));
    }

    public void testFindFirst(){
        List<User> userList = this.genUserList();
        Optional<User> optionalUser =userList.stream().findFirst();
        Optional.ofNullable(optionalUser).ifPresent(System.out::println);
    }



    private List<User> genUserList(){
        List<User> userList = Lists.newLinkedList();
        User user1 =User.builder().address("bj").age(10).userName("zhangsan").build();
        User user2 =User.builder().address("sh").age(10).userName("lisi").build();
        User user3 =User.builder().address("gz").age(10).userName("wangwu").build();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }




    public static void main(String[] args) {
        TestStream testStream = new TestStream();
        //testStream.testMap();
        //testStream.testFlatMap();
        //testStream.testFilter();
        //testStream.testForEach();
        testStream.testFindFirst();
    }




}
