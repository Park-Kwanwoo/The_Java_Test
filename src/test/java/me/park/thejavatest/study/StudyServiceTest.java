package me.park.thejavatest.study;

import me.park.thejavatest.domain.Member;
import me.park.thejavatest.domain.Study;
import me.park.thejavatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Test
    void createNewStudy(@Mock MemberService memberService,
                            @Mock StudyRepository studyRepository) {

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        // 이러한 객체를 리턴받고 싶다 Stubbing
        Member member = new Member();
        member.setId(1L);
        member.setEmail("gwan@email.com");

        // 호출이 되면 return 하라
        // any() : 어떠한 값이 들어오든지 무조건 정해진 객체 반환
        when(memberService.findById(any())).thenReturn(Optional.of(member));
        assertEquals("gwan@email.com", memberService.findById(1L).get().getEmail());
        assertEquals("gwan@email.com", memberService.findById(2L).get().getEmail());

        // 예외를 던지게함
        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

        memberService.validate(2L);

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });
        assertEquals(Optional.empty(), memberService.findById(3L));
    }

}