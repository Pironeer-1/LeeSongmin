package com.pironeer.mytemplatecode.member.repository;

import com.pironeer.mytemplatecode.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class MemberRepository {
    private final AtomicLong memberIdxGenerator = new AtomicLong(0);
    private final Map<Long, Member> memberMap = new HashMap<>();

    // 저장
    public Member save(Member member) {
        if (member.getMemberId() == null) {
            Long id = memberIdxGenerator.incrementAndGet();
            member.setId(id);
            memberMap.put(id, member);
            log.info("gd");
            for (Long l : memberMap.keySet()) {
                log.info(String.valueOf(memberMap.get(l)));
            }
            return member;
        } else {
            log.info("gd123123");
            Long id = memberIdxGenerator.incrementAndGet();
            member.setId(id);
            memberMap.put(member.getId(), member);
            //memberMap.replace(member.getId(), member);
            for (Long l : memberMap.keySet()) {
                log.info(String.valueOf(memberMap.get(l)));
            }
            return member;
        }
    }

    // id로 Member 조회
    public Optional<Member> findById(Long id) {
        Assert.notNull(id, "ID MUST NOT BE NULL");
        return Optional.ofNullable(memberMap.get(id));
    }

    // memberId로 Member 조회
    public Optional<Member> findByMemberId(String memberId) {
        return memberMap.values().stream()
                .filter(data -> data.getMemberId().equals(memberId))
                .findAny();
    }

    // memberId로 Member가 존재하는지 검사
    public Boolean existByMemberId(String memberId) {
        return memberMap.values().stream()
                .anyMatch(data -> data.getMemberId().equals(memberId));
    }
}

