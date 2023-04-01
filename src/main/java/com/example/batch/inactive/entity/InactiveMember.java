package com.example.batch.inactive.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class InactiveMember implements Persistable<Long> {

    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    private int age;

    private String phoneNumber;

    public InactiveMember(Long id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
