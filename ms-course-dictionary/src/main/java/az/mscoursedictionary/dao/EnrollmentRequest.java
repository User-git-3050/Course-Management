package az.mscoursedictionary.dao;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EnrollmentRequest {
    private String username;

}
