package com.zxsample.demo.application.model;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagingQueryResult<T> {
    private Integer total;
    private Integer pageNumber;
    private Integer pageSize;
    private List<T> data;
}
