package spring.security.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/member/v1")
public class MemberController {
    @RequestMapping(value = "/health")
    public String healthCheck() {
        return "난 건강해";
    }
}
