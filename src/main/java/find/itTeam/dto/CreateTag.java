package find.itTeam.dto;
import lombok.Data;
@Data
public class CreateTag {
    private String tag;
    //Id разработчика тут не нужно, т.к. это лишь создание, а не присвоение тега.
}
