package com.example.course.request;

import lombok.*;

import java.util.List;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertCourseRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    @Size(min = 51, message = " length must greater than 50")
    private String description;

    @NotBlank(message = "type is required")
    private String type;

    private List<String> topics;
    private String thumbnail;
    private int userId;
}
