package com.aspectj.demo.bean;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> @author:      谢文良
 * <br> Date:        2018/4/9 16:05
 */
public class Person {
    private String name;
    private int age;

    public Person() {
        name = "小明";
        age = 22;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + "name = " + name + "; age = " + age + "; ";
    }
}
