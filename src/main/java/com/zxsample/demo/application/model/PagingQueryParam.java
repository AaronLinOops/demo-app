package com.zxsample.demo.application.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagingQueryParam {
    private Integer pageNumber;
    private Integer pageSize;
}
