package me.park.thejavatest;

import org.junit.jupiter.api.*;

// class 마다 인스턴스를 생성 : 현재 클래스안의 모든 메서드가 값을 공유
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestInstanceTest {

    // 테스트 메서드마다 인스턴스를 새로 만든다
    // 메서드 간의 의존성을 없애기 위해서
    // 결과적으로 value의 값은 변화하지 않는다.
    // 기본적으로는 선언되어 있는 순으로 실행되지만 매번 그렇다고 보장할 순 없다.
    int value = 1;

    @Test
    @DisplayName("스터디 만들기 fast")
    void create_new_study() {
        System.out.println(this);
        System.out.println(value++);

    }

    @Test
    @DisplayName("스터디 만들기 slow")
    void create_new_study_again() {
        System.out.println(this);
        System.out.println("create " + value++);
    }


    @BeforeAll
    void beforeAll() {

        System.out.println("Before All");
    }

    @AfterAll
    void afterAll() {
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
