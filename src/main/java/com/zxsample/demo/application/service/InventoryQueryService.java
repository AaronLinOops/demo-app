package com.zxsample.demo.application.service;

import com.zxsample.demo.application.model.InventoryDTO;
import com.zxsample.demo.application.model.PagingQueryParam;
import com.zxsample.demo.application.model.PagingQueryResult;

public interface InventoryQueryService {

    PagingQueryResult<InventoryDTO> listInventories(PagingQueryParam pagingQueryParam);
}
