package practice.web_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.web_app.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
