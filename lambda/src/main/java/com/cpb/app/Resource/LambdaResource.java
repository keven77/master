package com.cpb.app.Resource;

import com.cpb.app.Dao.Person;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import org.springframework.util.Assert;


import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaResource {
    /**
     * 转换大小写
     * @return
     */
    public List lambda1() {
        String[] wordStr = {"a", "b", "c", "d"};
        List<String> result = Arrays.stream(wordStr).map(t -> t.toUpperCase()).collect(Collectors.toList());
        return result;
    }

    /**
     * 平方
     * @return
     */
    public List lambda2() {
        Integer[] wordStr = {1,2,3,4};
        List<Integer> result = Arrays.stream(wordStr).map(t->t*t).collect(Collectors.toList());
        return result;
    }
    public List lambda3() {
        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1),Arrays.asList(2,3),Arrays.asList(4,5,6));
        Stream<Integer> outputStream = inputStream.flatMap((t)->t.stream());
        List<Integer> result = outputStream.map(t->t*t).collect(Collectors.toList());
        return result;
    }

    /**
     * filter
     * @param
     */
    public List lambda4() {
        Integer[] arr = {1,2,3,4,5,6};
        Integer[] result = Arrays.stream(arr).filter(t->t%2==0).toArray(Integer[]::new);
        List resultList = Arrays.asList(result);
        return resultList;
    }

    /**
     * sorted
     * @param
     */
    public List lambda5() {
        List<Person> persons = Lists.newArrayList();
        for(int i=0;i<=5;i++) {
            Person person = new Person(i,"name"+i);
            person.setName("name"+i);
            persons.add(person);
        }
        List<Person> result = persons.stream().limit(3).sorted(Comparator.comparing(Person::getName).reversed()).collect(Collectors.toList());
        return result;
    }

    /**
     * match
     * @param
     */
    public Boolean lambda6() {
        List<Person> person = Lists.newArrayList();
        person.add(new Person("name" + 1, 10));
        person.add(new Person("name" + 2, 19));
        person.add(new Person("name" + 3, 20));
        person.add(new Person("name" + 4, 30));
        List<Integer> list = person.stream().filter(t->t.getAge()>12).map(Person::getAge).collect(Collectors.toList());
        System.out.println(list);
        boolean isAllAdult = person.stream().allMatch(t -> t.getAge() > 18);
        System.out.println("All are adult?" + " " + isAllAdult);
        boolean isAnyChild = person.stream().anyMatch(t -> t.getAge() < 12);
        System.out.println("Any child?" + " " + isAnyChild);
        return isAllAdult;
    }

    public static void main(String[] args) {
        /*Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(3).forEach(System.out::println);
        Stream.iterate(0,t->t+3).limit(10).forEach(t->System.out.println(t));
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        int sum = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        sum = Stream.of(1,2,3,4).reduce(Integer::sum).get();
        System.out.println(concat);
        Integer i = 3;
        Integer j = 4;
        Stream.of(i,j).sorted((a,b)->j.compareTo(i)).forEach(System.out::println);
        */
        //Integer[] arr =  {1,2,3};
        //List b = Lists.newArrayList();
        //Optional<Integer> sumOp = Arrays.stream(arr).reduce(Integer::sum);
        //System.out.println(sumOp);
        //sumOp.ifPresent(b::add);
        //List list = Arrays.asList(arr);
        //Stream stream = Stream.of(list);
        //System.out.println(stream);
        //System.out.println(b);
        LambdaResource lambdaResource = new LambdaResource();
        Assert.notNull(lambdaResource.lambda6(),"没有打印出任何信息");
        System.out.println(lambdaResource.lambda6());
    }
}
