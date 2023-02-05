package find.itTeam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс для изменения любого поля для пользователя
 */
@Data
@Accessors(chain = true)
public class UpdateFieldOfUser {
    private String field;
}