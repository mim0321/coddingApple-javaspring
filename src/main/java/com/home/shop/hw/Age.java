package com.home.shop.hw;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Age {
    private Integer age;
    private String name;

    public void 한살더하기(){
        if (age >= 0 && age < 100){
            this.age = this.age + 1;
            System.out.println("한살더하기 완료");
        } else {
            System.out.println("한살더하기 실패");
        }
    }
    public void 나이설정(Integer a){
        if (0 <= a && a < 100){
            this.age = a;
        } else {
            System.out.println("error");
        }
    }
}