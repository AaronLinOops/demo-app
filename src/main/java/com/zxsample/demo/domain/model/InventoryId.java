package com.zxsample.demo.domain.model;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryId {

    public static InventoryId of(String bizId) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(bizId), "biz id cannot be EMPTY");
        return new InventoryId(bizId);
    }

    private String bizId;
}
