package me.park.thejavatest;

import org.junit.jupiter.api.*;

public class CustomTagTest {

    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study() {

    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
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
