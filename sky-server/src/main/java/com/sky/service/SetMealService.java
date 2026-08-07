package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService {

    /**
     * 根据套餐id查询
     * @param id
     */
    SetmealVO getBySetMealIdReturnSetmealVO(Long id);

    /**
     * 新增套餐信息
     * @param setmealVO
     */
    void save(SetmealVO setmealVO);

    /**
     *
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 修改套餐信息
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 修改套餐起售状态
     * @param status
     * @param setmealId
     */
    void startOrStop(Integer status, Long setmealId);
}
