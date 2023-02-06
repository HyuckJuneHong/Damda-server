package kr.co.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ResponseUserDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TOKEN{
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ_USER_DETAIL {
        private String name;

        private String email;

        private List<ResponseOrderDto.READ_ORDER_INFO> readOrders;
    }
}
