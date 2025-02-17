package com.blogging.blogging.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private Integer pageSize;
    private Integer pageNumber;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;

}
