package com.home.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer price;
    private String user;
    private String imgURL;

    /** [코드 관리 도와주는 문법(java)]
     * 클래스 변수, 함수에 붙이는 modifier
     * 1. public 붙이면 모든 곳에서 사용가능
     * 2. 안붙이면 package-private == 같은 폴더의 클래스에서만 사용가능
     * 3. private 붙이면 다른 클래스에서 사용 불가함
     * 4. protected 붙이면 package-private과 같음(예외 : 상속한 클래스는 맘대로 사용가능)
     * 5. static 붙이면 클래스.변수 이렇게 직접 사용가능함 >> Item.title (원래는 new 키워드로 복사본을 써야함)
     * */
}

/** Lombok 플러그인 활용한 DB data 출력하기
 * 1. repository interface 만들기 ==> ItemRepository.java 파일 확인
 * 2. DB 입출력을 원하는 클래스에서 repository 등록
 * 3. DB 입출력 문법 사용
 * */