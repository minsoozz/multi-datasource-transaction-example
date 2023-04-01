package com.example.batch.active.service;

import com.example.batch.active.entity.ActiveMember;
import com.example.batch.active.repository.ActiveMemberRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActiveMemberService {

    private final ActiveMemberRepository activeMemberRepository;

    public JPAQuery<ActiveMember> activeMemberList() {
        return activeMemberRepository.getAll();
    }

    public void save(ActiveMember activeMember) {
        activeMemberRepository.save(activeMember);
    }

    public List<ActiveMember> findAll() {
        return activeMemberRepository.findAll();
    }
}
