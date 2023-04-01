package com.example.batch.inactive.service;

import com.example.batch.active.entity.ActiveMember;
import com.example.batch.inactive.entity.InactiveMember;
import com.example.batch.inactive.repository.InactiveMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InactiveMemberService {

    private final InactiveMemberRepository inactiveMemberRepository;

    public void toInactiveMember(ActiveMember activeMember) {
        inactiveMemberRepository.save(new InactiveMember(activeMember.getId(), activeMember.getName(), activeMember.getAge(), activeMember.getPhoneNumber()));
    }

}
