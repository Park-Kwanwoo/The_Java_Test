package me.park.thejavatest;

import org.junit.jupiter.api.*;

class TaggingTest {

    // 원하는 Tag가 달린 테스트만 실행할 수 있음

    @Test
    @DisplayName("스터디 만들기 fast")
    // 상황 : Local 환경에서 실행
    @Tag("fast")
    void create_new_study() {

    }

    @Test
    @DisplayName("스터디 만들기 slow")
    // 상황 : CI 환경에서 실행
    @Tag("slow")
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
