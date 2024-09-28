package az.mscourse.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorMessage {
    CLIENT_ERROR("Client error happened");

    private String message;
}
