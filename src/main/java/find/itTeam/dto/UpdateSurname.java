package find.itTeam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс для изменения ТОЛЬКО фамилии
 */
@Data
@Accessors(chain = true)
public class UpdateSurname {
    private String surname;
}