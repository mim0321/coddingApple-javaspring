package com.home.shop.hw;

public class TestAge {
    public static void main(String[] args) {
        Age a = new Age();
        a.setName("홍길동");
        a.setAge(99);
        a.나이설정(1);
        a.한살더하기();

        System.out.println("나이 : " + a.getAge());
        System.out.println("이름 : " + a.getName());
    }
}
