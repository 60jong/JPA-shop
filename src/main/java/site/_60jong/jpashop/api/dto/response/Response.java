package site._60jong.jpashop.api.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Response<T> {
    private LocalDateTime timestamp;
    private T data;

    public Response(T data) {
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
}
