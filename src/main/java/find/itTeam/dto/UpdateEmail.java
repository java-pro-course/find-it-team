package find.itTeam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс для изменения ТОЛЬКО email
 */
@Data
@Accessors(chain = true)
public class UpdateEmail {
    private String email;
}