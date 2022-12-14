package me.park.thejavatest.study;

import me.park.thejavatest.domain.Member;
import me.park.thejavatest.domain.Study;
import me.park.thejavatest.member.MemberService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
class StudyServiceTest {

    @Mock MemberService memberService;
    @Autowired
    StudyRepository studyRepository;

    @Container
    // static 으로 공유를 하지 않으면 매 테스트 마다 띄우고 끄기 때문에 속도가 심하게 저하 됨
    static GenericContainer postgreSQLContainer = new GenericContainer()
            .withExposedPorts(5432)
            .withEnv("POSTGRES_DB", "studytest");

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("===============");
        System.out.println(postgreSQLContainer.getMappedPort(5432));
        studyRepository.deleteAll();
    }
    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    @Test
    void createNewStudy() {

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);


        Member member = new Member();
        member.setId(1L);
        member.setEmail("gwan@email.com");

        Study study = new Study(10, "spring");

        // 호출이 되면 return 하라
        // any() : 어떠한 값이 들어오든지 무조건 정해진 객체 반환
        // 이러한 객체를 리턴받고 싶다 Stubbing
        when(memberService.findById(any())).thenReturn(Optional.of(member));
        assertEquals("gwan@email.com", memberService.findById(1L).get().getEmail());
        assertEquals("gwan@email.com", memberService.findById(2L).get().getEmail());

        when(studyRepository.save(study)).thenReturn(study);

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