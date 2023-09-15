package site._60jong.jpashop.presentation.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
}
