package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdByDishId(List<Long> dishIds);


    /**
     * 根据套餐id查询
     * @param setmealId
     */
    List<SetmealDish> getBySetmealId(Long setmealId);

    /**
     * 新增套餐菜品关联信息
     * @param setmealDishes
     */
    void save(List<SetmealDish> setmealDishes);

    /**
     * 批量删除套餐关联菜品信息
     * @param setmealIds
     */
    void deleteBatch(List<Long> setmealIds);
}
