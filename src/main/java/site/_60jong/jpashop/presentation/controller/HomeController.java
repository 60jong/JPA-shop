package site._60jong.jpashop.presentation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import site._60jong.jpashop.domain.item.ItemType;

@Slf4j
@Controller
public class HomeController {

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @GetMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }
}
