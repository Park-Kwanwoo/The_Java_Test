package me.park.thejavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;


class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {

        // 특정 시간안에 실행이 완료되는지 확인
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

//        예외 발생 확인
//        IllegalArgumentException exception =
//                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
//        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());


//        Study study = new Study(-10);

//        모든 테스트를 한번에 묶어서
//        assertAll(
//                () -> Assertions.assertNull(study, () -> "널이 아입니다."),
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "처음 스터디를 만들면 상태 값은 " +  StudyStatus.DRAFT + "여야 한다."),
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
//        );

//        Assertions.assertNotNull(study);
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), "처음 스터디를 만들면 상태 값은 DRAFT여야 한다.");

//        Lambda 식으로 적용했을 때, 연산을 필요한 시점에 함
//        이 경우에는 실패 시에만 문자열 연산을 실행
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "처음 스터디를 만들면 상태 값은 " +  StudyStatus.DRAFT + "여야 한다.");

//        다음 조건이 참인지 확인
//        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.");
    }

    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE32")
    void create_new_study_again() {

        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }
}