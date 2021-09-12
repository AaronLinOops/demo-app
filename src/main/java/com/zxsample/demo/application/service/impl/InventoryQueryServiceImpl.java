package com.zxsample.demo.application.service.impl;

import com.zxsample.demo.application.convert.InventoryConverter;
import com.zxsample.demo.application.model.InventoryDTO;
import com.zxsample.demo.application.model.PagingQueryParam;
import com.zxsample.demo.application.model.PagingQueryResult;
import com.zxsample.demo.application.service.InventoryQueryService;
import com.zxsample.demo.infrastructure.dal.dataobject.InventoryDO;
import com.zxsample.demo.infrastructure.dal.dataobject.InventoryDOExample;
import com.zxsample.demo.infrastructure.dal.mapper.InventoryDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {

    @Autowired
    private InventoryDOMapper mapper;

    @Override
    public PagingQueryResult<InventoryDTO> listInventories(PagingQueryParam param) {
        InventoryDOExample query = new InventoryDOExample().createCriteria().example();
        int total = (int) mapper.countByExample(query);
        if (total <= 0) {
            return PagingQueryResult.<InventoryDTO>builder()
                    .total(0).pageNumber(param.getPageNumber()).pageSize(param.getPageSize())
                    .build();
        }
        List<InventoryDO> inventoryDOList = mapper.selectByExampleWithBLOBs(query.page(param.getPageNumber(), param.getPageSize()));
        return PagingQueryResult.<InventoryDTO>builder()
                .data(InventoryConverter.toInventoryDTOList(inventoryDOList))
                .total(total).pageNumber(param.getPageNumber()).pageSize(param.getPageSize())
                .build();
    }
}
