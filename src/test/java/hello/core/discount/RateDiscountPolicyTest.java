package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_o() {
        // given vip멤버가 가입을했어
        Member member = new Member(1L, "user1", Grade.VIP);

        // when 30000원짜리 상품을 샀어
        int discount = discountPolicy.discount(member, 30000);

        // then 그럼 얼마가 할인되어야해?
        Assertions.assertThat(discount).isEqualTo(3000);
    }

    @Test
    @DisplayName("VIP가 아닌경우 할인이 적용되지 말아야 한다")
    void vip_x() {
        // given Basic멤버가 가입을했어
        Member member = new Member(1L, "user1", Grade.BASIC);

        // when 30000원짜리 상품을 샀어
        int discount = discountPolicy.discount(member, 30000);

        // then 그럼 얼마가 할인되어야해?
        Assertions.assertThat(discount).isEqualTo(0);
    }


}