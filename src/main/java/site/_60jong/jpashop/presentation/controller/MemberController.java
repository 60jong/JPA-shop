package site._60jong.jpashop.presentation.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import site._60jong.jpashop.application.service.MemberService;
import site._60jong.jpashop.domain.Address;
import site._60jong.jpashop.domain.Member;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();

        List<MemberDto> memberDtos = members.stream()
                .map(MemberDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("memberDtos", memberDtos);
        return "members/memberList";
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member(form.getName(), address);

        // 회원 등록
        memberService.join(member);
        return "redirect:/";
    }

    @Getter
    @AllArgsConstructor
    public static class MemberDto {
        private Long id;
        private String name;
        private String city;
        private String street;
        private String zipcode;

        public static MemberDto toDto(Member member) {
            Address address = member.getAddress();
            return new MemberDto(member.getId(), member.getUsername(), address.getCity(), address.getStreet(), address.getZipcode());
        }
    }
}
