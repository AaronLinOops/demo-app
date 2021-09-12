package com.zxsample.demo.api.controller;

import com.zxsample.demo.api.model.Result;
import com.zxsample.demo.application.model.InventoryDTO;
import com.zxsample.demo.application.model.PagingQueryParam;
import com.zxsample.demo.application.model.PagingQueryResult;
import com.zxsample.demo.application.service.InventoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryManagerController {
    @Autowired
    private InventoryQueryService inventoryQueryService;

    @GetMapping("/inventories")
    public Result<PagingQueryResult<InventoryDTO>> listInventories(
            @RequestParam(required=false, value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(required=false, value = "pageSize", defaultValue = "100") Integer pageSize) {
        try {
            PagingQueryResult<InventoryDTO> queryResult =
                    inventoryQueryService.listInventories(PagingQueryParam.builder()
                            .pageNumber(pageNumber)
                            .pageSize(pageSize)
                            .build());
            return Result.success(queryResult);
        } catch (Throwable t) {
            return Result.failed("INNER_ERROR", t.getMessage());
        }
    }
}
