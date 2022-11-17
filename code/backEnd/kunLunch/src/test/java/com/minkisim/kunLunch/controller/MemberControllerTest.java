package com.minkisim.kunLunch.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.minkisim.kunLunch.dto.MemberFormDto;
import com.minkisim.kunLunch.entity.Member;
import com.minkisim.kunLunch.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String memberID, String password){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setMemberID(memberID);
        memberFormDto.setEmail("1@1.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setPassword(password);
        Member member = Member.createMember(memberFormDto,passwordEncoder);
        return memberService.saveMember(member);

    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception{
        String memberID = "2022000000";
        String password = "1234";
        this.createMember(memberID, password);
        mockMvc.perform(formLogin().userParameter("memberID")
                        .loginProcessingUrl("/members/login")
                        .user(memberID).password(password))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws  Exception{
        String memberID = "2022000000";
        String password = "1234";
        this.createMember(memberID,password);
        mockMvc.perform(formLogin().userParameter("email")
                        .loginProcessingUrl("/members/login")
                        .user(memberID).password("12345"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

}