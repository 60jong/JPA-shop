package site._60jong.jpashop.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site._60jong.jpashop.api.dto.CreateItemRequest;
import site._60jong.jpashop.api.dto.response.Response;
import site._60jong.jpashop.application.service.ItemService;
import site._60jong.jpashop.config.annotation.CreateItemRequestBody;
import site._60jong.jpashop.domain.item.Item;
import site._60jong.jpashop.persistence.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ItemApiController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("/item")
    public Response<List<SearchItemResponse>> item(@RequestParam String name) {
        List<Item> items = itemRepository.findContainsName(name);

        return new Response<>(items.stream()
                .map(SearchItemResponse::toDto)
                .collect(Collectors.toList()));
    }

    @PostMapping("/item")
    public Response<Long> register(@CreateItemRequestBody CreateItemRequest request) {
        Long id = itemService.create(request.toEntity());
        return new Response<>(id);
    }

    @Data
    static class SearchItemResponse {
        private String name;
        private int price;
        private int stockQuantity;

        private SearchItemResponse(String name, int price, int stockQuantity) {
            this.name = name;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }

        public static SearchItemResponse toDto(Item item) {
            return new SearchItemResponse(item.getName(), item.getPrice(), item.getStockQuantity());
        }
    }
}
