package by.wevioz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class InterviewDto {
    @NotNull
    String instanceId;

    String interviewData;

    String status;

    public InterviewDto(String instanceId) {
        this.instanceId = instanceId;
    }
}
