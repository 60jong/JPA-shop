package site._60jong.jpashop.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.jpashop.api.dto.MemberResponse;
import site._60jong.jpashop.api.dto.response.Response;
import site._60jong.jpashop.application.service.MemberService;
import site._60jong.jpashop.persistence.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public Response<List<MemberResponse>> members() {
        List<MemberResponse> collect = memberRepository.findAll()
                .stream()
                .map(MemberResponse::toDto)
                .collect(Collectors.toList());

        return new Response<>(collect);
    }
}