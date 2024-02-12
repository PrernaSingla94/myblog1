package com.myblog1.myblog1.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class test {


    public static void main(String[] args) {

        List<Employee> employees=Arrays.asList(
            new Employee("adam",34,"chennai"),
                new Employee("mike",34,"chennai"),
                new Employee("sam",28,"pune"),
                new Employee("stallin",31,"bangalore")

        );
        Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getAge()));

for(Map.Entry<Integer,List<Employee>> entry:collect.entrySet()){
   int age= entry.getKey();
   List<Employee> e=entry.getValue();
   System.out.println("age:"+age+"---");
   for(Employee emp:e){
       System.out.println(emp.getName());
       System.out.println(emp.getCity());


    }

}
    }
}