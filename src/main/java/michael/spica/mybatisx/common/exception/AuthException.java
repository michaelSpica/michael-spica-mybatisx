package michael.spica.mybatisx.common.exception;

import lombok.Getter;
import michael.spica.mybatisx.common.enums.AuthErrorType;

/**
 * 鉴权异常
 * <p>
 * Created by michael on 2025-11-21.
 */
@Getter
public class AuthException extends RuntimeException {

    private final AuthErrorType type;

    public AuthException(AuthErrorType type, String message) {
        super(message);
        this.type = type;
    }
}
