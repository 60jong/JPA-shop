package site._60jong.jpashop.domain.category;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> itemCategories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    // Constructor
    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    //== 비즈니스 로직 ==//

    public void addChildCategory(Category category) {
        this.children.add(category);
    }
    /**
     * item 추가
     */
    public void addItemCategory(ItemCategory itemCategory) {
        this.itemCategories.add(itemCategory);
    }
}
