package site._60jong.jpashop.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.jpashop.api.dto.OrderResponse;
import site._60jong.jpashop.api.dto.SimpleOrderResponse;
import site._60jong.jpashop.api.dto.response.Response;
import site._60jong.jpashop.persistence.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/orders/simple")
    public Response<List<SimpleOrderResponse>> simpleOrders() {
        List<SimpleOrderResponse> collect = orderRepository.findAll()
                .stream()
                .map(SimpleOrderResponse::new)
                .collect(Collectors.toList());

        return new Response<>(collect);
    }

    @GetMapping("/orders/simple/fetch")
    public Response<List<SimpleOrderResponse>> fetchOrders() {
        List<SimpleOrderResponse> collect = orderRepository.findAllWithFetch()
                .stream()
                .map(SimpleOrderResponse::new)
                .collect(Collectors.toList());

        return new Response<>(collect);
    }

    @GetMapping("/orders")
    public Response<List<OrderResponse>> orders() {
        List<OrderResponse> collect = orderRepository.findAllWithFetch()
                .stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());

        return new Response<>(collect);
    }
}