package tpo.dtos.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExecutionStatusDto {
    private Boolean status;

    private String additionInfo;

    public static ExecutionStatusDto createOkExecutionStatus(){
        return new ExecutionStatusDto()
                .setStatus(true)
                .setAdditionInfo("Операция выполнена корректно!");
    }

    public static ExecutionStatusDto createBadExecutionStatus(String additionInfo){
        return new ExecutionStatusDto()
                .setStatus(false)
                .setAdditionInfo(additionInfo);
    }
}
