package com.minkisim.kunLunch.service;

import com.minkisim.kunLunch.entity.Member;
import com.minkisim.kunLunch.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByMemberID(member.getMemberID());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String memberID) throws UsernameNotFoundException{
        Member member = memberRepository.findByMemberID(memberID);

        if(member == null){
            throw new UsernameNotFoundException(memberID);
        }
        return User.builder()
                .username(member.getMemberID())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();

    }
}
