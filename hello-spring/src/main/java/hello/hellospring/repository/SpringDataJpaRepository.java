package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRepository extends JpaRepository<Member, Long>, MemberRepository {
    //JPOL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
