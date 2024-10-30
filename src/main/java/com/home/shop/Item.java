package com.home.shop;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;
    public Integer price;
}

/** Lombok 플러그인 활용한 DB data 출력하기
 * 1. repository interface 만들기 ==> ItemRepository.java 파일 확인
 * 2. DB 입출력을 원하는 클래스에서 repository 등록
 * 3. DB 입출력 문법 사용
 * */