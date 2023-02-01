package find.itTeam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс для изменения ТОЛЬКО имени
 */
@Data
@Accessors(chain = true)
public class UpdateName {
    private String name;
}