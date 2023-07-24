package spring.security.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spring.security.dto.MemberLoginRequestDto;
import spring.security.dto.TokenInfo;
import spring.security.service.MemberService;


@Slf4j
@RestController
@RequestMapping(value ="/member/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/health")
    public String healthCheck() {
        log.info("memberLoginRequestDto : ");
        return "난 건강해";
    }

    @GetMapping(value = "/test")
    public String testCheck() {
        log.info("log test : from test get method");
        return "test";
    }

    @PostMapping(value = "/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        log.info("member login request :"+memberLoginRequestDto.getMemberId());
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        return memberService.login(memberId, password);
    }
}
