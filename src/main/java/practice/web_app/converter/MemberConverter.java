package practice.web_app.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import practice.web_app.base.Code;
import practice.web_app.base.exception.MemberException;
import practice.web_app.domain.Member;
import practice.web_app.repository.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MemberConverter {

    private final MemberRepository memberRepository;

    private static MemberRepository staticMemberRepository;

    @PostConstruct
    public void init() {
        this.staticMemberRepository = memberRepository;
    }

    public static Member toMember(Long memberId) {
        return staticMemberRepository.findById(memberId).orElseThrow(() -> new MemberException(Code.MEMBER_NOT_FOUND));
    }
}
