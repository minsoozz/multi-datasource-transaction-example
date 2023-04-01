package com.example.batch.active.repository;

import com.example.batch.active.entity.ActiveMember;
import com.querydsl.jpa.impl.JPAQuery;

public interface CustomActiveMemberRepository {

    JPAQuery<ActiveMember> getAll();
}
