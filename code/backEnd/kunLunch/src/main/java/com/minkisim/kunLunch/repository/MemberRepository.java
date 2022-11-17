package com.minkisim.kunLunch.repository;


import com.minkisim.kunLunch.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMemberID(String memberID);
}
