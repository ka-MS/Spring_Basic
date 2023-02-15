package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        int userA = statefulService1.order("user1", 10000);
        // ThreadB: B사용자 20000원 주문
        int userB = statefulService2.order("user2", 20000);
        // ThreadA: 사용자A 주문금액 조회 a가 주문하고 결제하는 사이에 b가 결제함 
        // 사용자a가 조회했음에도 20000원이나옴 사용자b가 바꿔버림 같은 참조를 사용하기때문에
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userA);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}