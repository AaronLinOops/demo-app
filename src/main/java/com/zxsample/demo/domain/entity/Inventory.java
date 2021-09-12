package com.zxsample.demo.domain.entity;

import com.zxsample.demo.domain.model.InventoryDetail;
import com.zxsample.demo.domain.model.InventoryId;
import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Inventory {
    private InventoryId id;
    private String name;
    private Integer remains;
    private InventoryDetail detail;
}
