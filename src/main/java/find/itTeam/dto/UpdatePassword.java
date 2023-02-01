package find.itTeam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс для изменения ТОЛЬКО пароля
 */
@Data
@Accessors(chain = true)
public class UpdatePassword {
    private String password;
}