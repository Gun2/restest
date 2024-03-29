package com.gun2.restest.form.request;

import com.gun2.restest.constant.Method;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JobRequest implements Serializable {
    private Long id;
    @NotBlank
    @Length(max = 255)
    private String title;
    @NotNull
    private Method method;
    @URL
    @NotBlank
    @Length(max = 255)
    private String url;
    private String color;
    @Valid
    private List<JobParamRequest> jobParamList = new ArrayList<>();
    @Valid
    private List<JobHeaderRequest> jobHeaderList = new ArrayList<>();
    @Valid
    private List<JobBodyRequest> jobBodyList = new ArrayList<>();
}
