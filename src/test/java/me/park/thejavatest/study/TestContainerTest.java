package me.park.thejavatest.study;

import me.park.thejavatest.domain.Member;
import me.park.thejavatest.member.MemberService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
public class TestContainerTest {

    @Mock
    MemberService memberService;

    @Autowired
    StudyRepository studyRepository;

    @Container
    // static 으로 공유를 하지 않으면 매 테스트 마다 띄우고 끄기 때문에 속도가 심하게 저하 됨
    static GenericContainer postgreSQLContainer = new GenericContainer()
            .withExposedPorts(5432)
            .withEnv("POSTGRES_DB", "studytest")
            .waitingFor(Wait.forListeningPort());


    @BeforeEach
    void beforeEach() {
        System.out.println("===============");
        System.out.println(postgreSQLContainer.getMappedPort(5432));
        postgreSQLContainer.start();
        studyRepository.deleteAll();
    }

    @Test
    @DisplayName("컨테이너 실행 테스트")
    void createNewStudy() {

        Optional<Member> byId = memberService.findById(1L);

        assertEquals(1L, byId.get().getId());
    }
}
