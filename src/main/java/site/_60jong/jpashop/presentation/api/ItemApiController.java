package site._60jong.jpashop.presentation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site._60jong.jpashop.application.service.ItemService;
import site._60jong.jpashop.config.annotation.ItemRequestBody;
import site._60jong.jpashop.domain.item.Item;
import site._60jong.jpashop.presentation.api.dto.ItemRequest;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/item")
    public List<ResponseEntity<ItemSearchResponseDto>> item(@RequestParam String name) {
        List<Item> items = itemService.findByName(name);

        return items.stream()
                .map(ItemSearchResponseDto::toDto)
                .map(dto -> ResponseEntity.ok(dto))
                .collect(Collectors.toList());
    }

    @PostMapping("/item")
    public ResponseEntity<Long> register(@ItemRequestBody ItemRequest dto) {
        Long id = itemService.create(dto.toEntity());
        return ResponseEntity.ok(id);
    }

    static class ItemSearchResponseDto {
        private String name;
        private int price;
        private int stockQuantity;

        private ItemSearchResponseDto(String name, int price, int stockQuantity) {
            this.name = name;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }

        private static ItemSearchResponseDto toDto(Item item) {
            return new ItemSearchResponseDto(item.getName(), item.getPrice(), item.getStockQuantity());
        }
    }
}