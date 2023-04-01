package com.example.batch.inactive.service;

import com.example.batch.active.entity.ActiveMember;
import com.example.batch.active.repository.ActiveMemberRepository;
import com.example.batch.facade.MemberFacade;
import com.example.batch.inactive.entity.InactiveMember;
import com.example.batch.inactive.repository.InactiveMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class InactiveMemberServiceTest {

    @Autowired
    public InactiveMemberRepository inactiveMemberRepository;

    @Autowired
    public ActiveMemberRepository activeMemberRepository;

    @Autowired
    public MemberFacade memberFacade;

    @BeforeEach
    public void init() {
        activeMemberRepository.deleteAll();
        inactiveMemberRepository.deleteAll();
    }

    @Test
    void 런타임_오류가_발생_시_두개의_데이터베이스가_모두_롤백된다() {
        ActiveMember activeMember = new ActiveMember("minsoo", 1, "010-0000-0000");
        activeMemberRepository.save(activeMember);
        InactiveMember inActiveMember = new InactiveMember(1L, "minsoo", 1, "010-0000-0000");
        inactiveMemberRepository.save(inActiveMember);
    }
}