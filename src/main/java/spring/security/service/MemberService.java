package spring.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import spring.security.domain.Member;
import spring.security.dto.TokenInfo;
import spring.security.provider.JwtTokenProvider;
import spring.security.repository.MemberRepository;
import spring.security.vo.MemberVo;

import java.util.Optional;

@Service
@Transactional()
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenInfo login(String memberId, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    public MemberVo findUser(String memberId)
    {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        Member member1 = member.orElseGet(Member::new);

        MemberVo memberVo = new MemberVo();

        memberVo.setMemberId(member1.getMemberId());
        memberVo.setPassword(member1.getPassword());

        return memberVo;
    }
}
