package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {


    /**
     * 批量插入口味信息
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 关联批量删除菜品口味
     * @param dishIds
     */
    void deleteByDishId(List<Long> dishIds);

    /**]
     * 根据菜品id查询口味
     * @param dishId
     */
    List<DishFlavor> getByDishId(Long dishId);
}
