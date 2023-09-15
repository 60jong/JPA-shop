package site._60jong.jpashop.application.service.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site._60jong.jpashop.application.service.ItemService;
import site._60jong.jpashop.persistence.repository.ItemRepository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemFormBodyServiceTest {

    private ItemRepository itemRepository;
    private ItemService itemService;

    @BeforeEach
    void before() {
        itemRepository = mock(ItemRepository.class);
        itemService = new ItemService(itemRepository);
    }

    @Test
    @DisplayName("영화 저장")
    void create_movie() {
        // given
        Long saveMockMovieId = 1L;
        when(itemRepository.save(any())).thenReturn(saveMockMovieId);

        ItemService.MovieDto movieDto = new ItemService.MovieDto(
                "movieA",
                20000,
                1000,
                "directorA",
                "actorA"
        );

        // when
        Long movieId = itemService.create(movieDto);

        // then
        assertThat(movieId).isEqualTo(saveMockMovieId);
    }

    @Test
    @DisplayName("책 저장")
    void create_book() {
        // given
        Long saveMockBookId = 1L;
        when(itemRepository.save(any())).thenReturn(saveMockBookId);

        ItemService.BookDto bookDto = new ItemService.BookDto(
                "movieA",
                20000,
                1000,
                "authorA",
                "isbnA"
        );

        // when
        Long bookId = itemService.create(bookDto);

        // then
        assertThat(bookId).isEqualTo(saveMockBookId);
    }
}