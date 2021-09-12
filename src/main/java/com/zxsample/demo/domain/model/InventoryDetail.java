package com.zxsample.demo.domain.model;

import lombok.*;

/**
 * 库存详情
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InventoryDetail {
    private String description;
}
