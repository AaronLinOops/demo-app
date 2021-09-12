package com.zxsample.demo.application.convert;

import com.google.gson.Gson;
import com.zxsample.demo.application.model.InventoryDTO;
import com.zxsample.demo.domain.model.InventoryDetail;
import com.zxsample.demo.infrastructure.dal.dataobject.InventoryDO;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryConverter {

    public static List<InventoryDTO> toInventoryDTOList(List<InventoryDO> inventoryDOList) {
        if (inventoryDOList == null) {
            return null;
        }
        return inventoryDOList.stream()
                .map(InventoryConverter::toInventoryDTO)
                .collect(Collectors.toList());
    }

    private static InventoryDTO toInventoryDTO(InventoryDO inventoryDO) {
        return InventoryDTO.builder()
                .bizId(inventoryDO.getBizId())
                .name(inventoryDO.getName())
                .remains(inventoryDO.getCount())
                .detail(new Gson().fromJson(inventoryDO.getDetail(), InventoryDetail.class))
                .build();
    }
}
