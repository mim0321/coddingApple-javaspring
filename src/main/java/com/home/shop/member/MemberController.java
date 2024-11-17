package com.home.shop.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

//    @@@@@@ API @@@@@@

    //    Register API
    @GetMapping("/member/join")
    String register() {
        return "register.html";
    }

    @PostMapping("/member/join")
    String addMember(@RequestParam String username,
                     @RequestParam String displayName,
                     @RequestParam String password
    ) {

        var hash = passwordEncoder.encode(password);
        Member member = new Member();

        if (username.isEmpty() || displayName.isEmpty() || password.isEmpty()) {
            return "redirect:/member/join";
        } else if (username.length() > 20 || displayName.length() > 10 || password.length() > 32) {
            return "redirect:/member/join";
        } else {
            member.setUsername(username);
            member.setDisplayName(displayName);
            member.setPassword(hash);
//        암호는 1234로 통일함

            memberRepository.save(member);
            return "redirect:/";
        }
    }

}