package site._60jong.jpashop.application.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site._60jong.jpashop.domain.item.Book;
import site._60jong.jpashop.domain.item.Item;
import site._60jong.jpashop.persistence.repository.ItemRepository;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long create(Item item) {
        return itemRepository.save(item);
    }

    public Item findOne(Long itemId) {
        return itemRepository.find(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public void update(Long itemId, UpdateItemParam param) {
        Book book = (Book) itemRepository.find(itemId);
        book.modify(
                param.getName(),
                param.getPrice(),
                param.getStockQuantity(),
                param.getAuthor(),
                param.getIsbn()
        );
        itemRepository.save(book);
    }

    @Getter @Setter
    public static class UpdateItemParam {
        private String name;
        private int price;
        private int stockQuantity;
        private String author;
        private String isbn;
    }
}
