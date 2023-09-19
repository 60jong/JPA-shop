package site._60jong.jpashop.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import site._60jong.jpashop.application.service.ItemService;
import site._60jong.jpashop.application.service.MemberService;
import site._60jong.jpashop.application.service.OrderItemInformation;
import site._60jong.jpashop.application.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<MemberController.MemberDto> memberDtos = memberService.findMembers()
                .stream()
                .map(MemberController.MemberDto::toDto)
                .collect(Collectors.toList());

        List<ItemDto> itemDtos = itemService.findItems()
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("memberDtos", memberDtos);
        model.addAttribute("itemDtos", itemDtos);
        model.addAttribute("form", new OrderForm());
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String create(OrderForm form) {
        List<OrderItemInformation> informs = new ArrayList<>();
        informs.add(new OrderItemInformation(form.getItemId(), form.getCount()));
        orderService.order(form.getMemberId(), informs);
        return "redirect:/";
    }
}
