package com.gun2.restest.dto;

import com.gun2.restest.entity.JobBody;
import io.swagger.models.auth.In;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JobBodyDto implements Serializable {
    private Long id;
    private Long jobId;
    @NotNull
    private String body;
    @NotNull
    @Range(min = 0, max = 1000000)
    private Integer afterDelay;

    private boolean usable;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public JobBodyDto(JobBody jobBody){
        this.id = jobBody.getId();
        this.jobId = jobBody.getJobId();
        this.body = jobBody.getBody();
        this.afterDelay = jobBody.getAfterDelay();
        this.createdAt = jobBody.getCreatedAt();
        this.updateAt = jobBody.getUpdateAt();
        this.usable = jobBody.isUsable();
    }

    public JobBody toEntity(){
        return JobBody.builder()
                .id(this.id)
                .jobId(this.jobId)
                .body(this.body)
                .afterDelay(this.afterDelay)
                .usable(this.usable)
                .build();
    }
}
