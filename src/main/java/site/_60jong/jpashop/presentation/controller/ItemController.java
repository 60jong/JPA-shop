package site._60jong.jpashop.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site._60jong.jpashop.application.service.ItemService;
import site._60jong.jpashop.domain.item.Book;
import site._60jong.jpashop.domain.item.Item;
import site._60jong.jpashop.domain.item.ItemType;
import site._60jong.jpashop.api.dto.ItemForm;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();

        List<ItemDto> itemDtos = items.stream()
                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity()))
                .collect(Collectors.toList());

        model.addAttribute("itemDtos", itemDtos);
        return "items/itemList";
    }

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm.BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(ItemForm.BookForm bookForm) {
        Item item = bookForm.toEntity();
        itemService.create(item);

        return "redirect:/";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateForm(Model model, @PathVariable("itemId") Long itemId) {
        Book book = (Book) itemService.findOne(itemId);
        ItemForm.BookForm bookForm = new ItemForm.BookForm();
        bookForm.setId(book.getId());
        bookForm.setName(book.getName());
        bookForm.setPrice(book.getPrice());
        bookForm.setStockQuantity(book.getStockQuantity());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setIsbn(book.getIsbn());

        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String update(ItemForm.BookForm form, @PathVariable("itemId") Long itemId) {
        ItemService.UpdateItemParam param = new ItemService.UpdateItemParam();
        param.setName(form.getName());
        param.setPrice(form.getPrice());
        param.setStockQuantity(form.getStockQuantity());
        param.setAuthor(form.getAuthor());
        param.setIsbn(form.getIsbn());

        itemService.update(itemId, param);
        return "redirect:/";
    }
}
