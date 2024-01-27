package com.cengizhanyavuz.flightsearchapi.business.dto;

import jakarta.validation.constraints.NotEmpty;
import com.cengizhanyavuz.flightsearchapi.auditing.AuditingAwareBaseDto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// Validation

// CategoryDto(1) - BlogDto(N)
public class UserDto extends AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID=1L;

    @NotEmpty(message = "{name.validation.constraints.NotNull.message}")
    @Size(min=2,message = "{name.least.validation.constraints.NotNull.message}")
    private String taskName;
    @NotEmpty
    private String taskDescription;
    private Date dueDate;
    private String tags;
    private Long projectId;
}