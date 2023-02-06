package kr.co.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class RequestUserDto {

    @Getter
    @AllArgsConstructor
    public static class CREATE_USER{
        private String name;

        private String identity;

        private String password;

        private String checkPassword;

        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class LOGIN{
        private String identity;

        private String password;
    }
}
