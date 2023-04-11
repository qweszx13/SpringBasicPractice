package hello.core.order;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();// 이 코드 를 보면 DiscountPolicy 뿐만 아니라
//    FixDiscountPolicy 구체 클래스 까지 의존하고있는것을 알수있다. 이렇게되면 DIP를 위반하게 되는것이다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();// FixDiscountPolicy 를 RateDiscountPolicy
//    로 바꾸기위한 코드이지만 OrderServiceImpl에 코드 수정이 생김으로 이것은 OCP위반으로 이어지게된다.
//    DIP(Dependency Inversion Principle), OCP(Open Close Principle)
//    해결책은 아래와 같다.
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
