package kr.co.enums;

import java.util.Arrays;

public enum UserRole {

    ROLE_USER("일반"),
    ROLE_ADMIN("관리자");

    public String getRole() {
        return role;
    }

    UserRole(String role) {
        this.role = role;
    }

    private String role;

    public static UserRole of(String role){
        return Arrays.stream(UserRole.values())
                .filter(r -> r.toString().equalsIgnoreCase(role))
                .findAny().orElseThrow(() -> new RuntimeException("해당 권한은 존재하지 않습니다."));
    }
}