package kr.co.enums;

import java.util.Arrays;

public enum Gender {

    MALE("남성"),
    FEMALE("여성");

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    String gender;

    public static Gender of(String gender){
        return Arrays.stream(Gender.values())
                .filter(g -> g.toString().equalsIgnoreCase(gender))
                .findAny().orElseThrow(() -> new RuntimeException("해당 성별은 존재하지 않습니다."));
    }
}