package com.sio.poveacl.acl.dto;

import com.sio.poveacl.acl.domain.PoveStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDTO {

    private Long id;

    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    private PoveStatus status;

    private List<Long> features;


}
