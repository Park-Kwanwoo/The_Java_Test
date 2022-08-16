package me.park.thejavatest.member;

import me.park.thejavatest.domain.Member;
import me.park.thejavatest.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newStudy);
    void notify(Member member);
}
