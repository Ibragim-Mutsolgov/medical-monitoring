package liga.medical.medicalmonitoring.core.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ExceptionDto {

    private UUID uuid;

    private String systemTypeId;

    private String methodParams;
}
