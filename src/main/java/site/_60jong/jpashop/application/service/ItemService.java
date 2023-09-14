package site._60jong.jpashop.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<Item> findByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}
