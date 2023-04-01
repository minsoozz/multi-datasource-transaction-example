package com.example.batch.inactive.repository;

import com.example.batch.inactive.entity.InactiveMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InactiveMemberRepository extends JpaRepository<InactiveMember, Long> {
}
