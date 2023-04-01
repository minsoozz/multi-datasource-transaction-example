package com.example.batch.active.repository;

import com.example.batch.active.entity.ActiveMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveMemberRepository extends JpaRepository<ActiveMember, Long>, CustomActiveMemberRepository {
}
