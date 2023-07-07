package com.mertkurtoglu.business.dto;

import com.mertkurtoglu.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

public class TodoDto extends AuditingAwareBaseDto implements Serializable {
    public static final Long serialVersionUID=1L;

    @NotEmpty(message = "Task cannot be empty")
    @Size(min = 2, message = "Task cannot be less than 2 characters")
    private String task;
}
