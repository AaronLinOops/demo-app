package com.zxsample.demo.infrastructure.dal.mapper;

import com.zxsample.demo.infrastructure.dal.dataobject.InventoryDO;
import com.zxsample.demo.infrastructure.dal.dataobject.InventoryDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryDOMapper {
    long countByExample(InventoryDOExample example);

    int deleteByExample(InventoryDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InventoryDO record);

    int insertSelective(InventoryDO record);

    InventoryDO selectOneByExample(InventoryDOExample example);

    InventoryDO selectOneByExampleSelective(@Param("example") InventoryDOExample example, @Param("selective") InventoryDO.Column ... selective);

    InventoryDO selectOneByExampleWithBLOBs(InventoryDOExample example);

    List<InventoryDO> selectByExampleSelective(@Param("example") InventoryDOExample example, @Param("selective") InventoryDO.Column ... selective);

    List<InventoryDO> selectByExampleWithBLOBs(InventoryDOExample example);

    List<InventoryDO> selectByExample(InventoryDOExample example);

    InventoryDO selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") InventoryDO.Column ... selective);

    InventoryDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InventoryDO record, @Param("example") InventoryDOExample example);

    int updateByExampleWithBLOBs(@Param("record") InventoryDO record, @Param("example") InventoryDOExample example);

    int updateByExample(@Param("record") InventoryDO record, @Param("example") InventoryDOExample example);

    int updateByPrimaryKeySelective(InventoryDO record);

    int updateByPrimaryKeyWithBLOBs(InventoryDO record);

    int updateByPrimaryKey(InventoryDO record);

    int batchInsert(@Param("list") List<InventoryDO> list);

    int batchInsertSelective(@Param("list") List<InventoryDO> list, @Param("selective") InventoryDO.Column ... selective);

    int upsert(InventoryDO record);

    int upsertSelective(InventoryDO record);

    int upsertWithBLOBs(InventoryDO record);
}