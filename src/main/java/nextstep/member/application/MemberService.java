package nextstep.member.application;

import nextstep.auth.principal.UnknownUserPrincipal;
import nextstep.auth.principal.UserPrincipal;
import nextstep.member.application.dto.MemberRequest;
import nextstep.member.application.dto.MemberResponse;
import nextstep.member.domain.Member;
import nextstep.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse createMember(MemberRequest request) {
        Member member = memberRepository.save(request.toMember());
        return MemberResponse.of(member);
    }

    public MemberResponse findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
        return MemberResponse.of(member);
    }

    public void updateMember(Long id, MemberRequest param) {
        Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
        member.update(param.toMember());
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberResponse findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponse::of)
                .orElseThrow(RuntimeException::new);
    }

    public Member findMemberByUserPrincipal(UserPrincipal userPrincipal) {
        return memberRepository.findByEmail(userPrincipal.getUsername())
                .orElseThrow(RuntimeException::new);
    }
}