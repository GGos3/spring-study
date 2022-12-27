package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 수정자 주입
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    // 일반 메서드 주입
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    /*  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
            private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
            private final MemberRepository memberRepository = new MemoryMemberRepository();*/

    /*
    * 생성자 주입
    * 생성자가 1개라면 @Autowired 생략 가능
     * */
    @Autowired
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

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
