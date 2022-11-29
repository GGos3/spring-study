package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //DI
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    회원 가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        /*
                Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.")
        });
         */
        // 이미 Optional 형태로 반환하기 떄문에 .isPresent를 사용할 수 있다.
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }
    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    /*
    단일 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
