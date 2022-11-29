package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private MemberService memberService;
    //  필드 주입
//  @Autowired private final MemberService memberService;

    //setter 주입
//    @Autowired
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//        public 하기 때문에 아래와 같은 경우가 생겨 요즘은 잘 사용하지 않는다.
//        memberSerice.setMemberRepository();
//    }

    // 생성자 주입
    @Autowired  //spring 컨테이너가 시작할때 의존성을 주입함
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
