package site._60jong.jpashop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import site._60jong.jpashop.config.annotation.ItemRequestBody;
import site._60jong.jpashop.domain.item.ItemType;
import site._60jong.jpashop.presentation.api.dto.ItemRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Map;

@Slf4j
public class ItemRequestBodyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isItemDtoAnnotation = parameter.getParameterAnnotation(ItemRequestBody.class) != null;
        boolean isItemDtoClass = ItemRequest.class.equals(parameter.getParameterType());
        return isItemDtoAnnotation && isItemDtoClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = request.getReader();
        Map<Object, Object> map = objectMapper.readValue(reader, Map.class);
        String type = String.valueOf(map.get("type"));

        switch (ItemType.valueOf(type)) {
            case MOVIE:
                return objectMapper.convertValue(map, ItemRequest.MovieRequest.class);

            case BOOK:
                return objectMapper.convertValue(map, ItemRequest.BookRequest.class);
        }
        throw new IllegalArgumentException();
    }
}
