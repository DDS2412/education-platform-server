package tpo.dtos.simplified;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class SimplifiedMessageDto {
    private Integer id;

    private String text;

    private String senderName;

    private String metaInformation;

    private String type;
}
