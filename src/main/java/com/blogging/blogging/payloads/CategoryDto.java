package com.blogging.blogging.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    private String CategoryTitle;
    @NotBlank
    @Size(min = 5,message = "description must be greater than 5")
    private String CategoryDescription;
}
