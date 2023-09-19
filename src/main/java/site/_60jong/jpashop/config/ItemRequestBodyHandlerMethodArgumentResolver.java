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
import site._60jong.jpashop.api.dto.ItemForm;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class ItemRequestBodyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isItemDtoAnnotation = parameter.getParameterAnnotation(ItemRequestBody.class) != null;
        boolean isItemDtoClass = ItemForm.class.equals(parameter.getParameterType());
        return isItemDtoAnnotation && isItemDtoClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<Object, Object> bodyMap = getBodyMap(webRequest);
        return getItemRequest(bodyMap);
    }

    private ItemForm getItemRequest(Map<Object, Object> map) {
        String type = (String) map.get("type");

        switch (ItemType.valueOf(type)) {
            case MOVIE:
                return objectMapper.convertValue(map, ItemForm.MovieForm.class);

            case BOOK:
                return objectMapper.convertValue(map, ItemForm.BookForm.class);
        }
        throw new IllegalArgumentException();
    }

    private Map<Object, Object> getBodyMap(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        return objectMapper.readValue(request.getReader(), Map.class);
    }
}
