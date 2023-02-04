package kr.co.error.model;

import kr.co.enums.ErrorCode;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;

    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatus())
                .build();
    }

    public static ErrorResponse of(String errorMessage){
        return ErrorResponse.builder()
                .message(errorMessage)
                .status(400)
                .build();
    }
}
