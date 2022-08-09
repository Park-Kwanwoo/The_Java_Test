package me.park.thejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// 선언을 통한 Extension 사용
// Customizing 불가능
//@ExtendWith(FindSlowTestExtension.class)
public class OrderOfTest {

    // 필드 정의 (static 필수)
    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    int value = 0;

    @Order(2)
    @Test
    @DisplayName("스터디 만들기 fast")
    void create_new_study() {
        System.out.println(this);
        System.out.println("fast later " + value++);

    }

    @Order(1)
    @Test
    @DisplayName("스터디 만들기 slow")
    void create_new_study_again() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println(this);
        System.out.println("slow first " + value++);
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
