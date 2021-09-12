package com.zxsample.demo.application.model;

import com.zxsample.demo.domain.model.InventoryDetail;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private String bizId;
    private String name;
    private Integer remains;
    private InventoryDetail detail;
}
