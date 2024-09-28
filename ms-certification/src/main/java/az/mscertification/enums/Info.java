package az.mscertification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Info {
    CERTIFICATIONS_WERE_GENERATED("Certifications were generated"),;

    private final String message;
}
