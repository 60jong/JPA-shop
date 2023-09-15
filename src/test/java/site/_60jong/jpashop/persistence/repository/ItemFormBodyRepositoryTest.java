package site._60jong.jpashop.persistence.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site._60jong.jpashop.domain.item.Item;
import site._60jong.jpashop.domain.item.Movie;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemFormBodyRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("영화 저장 시 DB에 영화 생성")
    void save_movie() {
        // given
        Movie movie = new Movie("movieA", 20000, 100, "directorA", "actorA");

        // when
        Long movieId = itemRepository.save(movie);
        Item findMovie = itemRepository.find(movieId);

        // then
        Assertions.assertThat(findMovie).isInstanceOf(Movie.class);
    }
}