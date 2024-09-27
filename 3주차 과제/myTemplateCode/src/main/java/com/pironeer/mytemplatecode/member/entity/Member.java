package com.pironeer.mytemplatecode.member.entity;

import lombok.Builder;
import lombok.Data;

@Data
public class Member {

    private Long id;
    private String memberId;
    private String password;
    private String name;

    @Builder
    public Member(String memberId, String password, String name) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
    }
}

