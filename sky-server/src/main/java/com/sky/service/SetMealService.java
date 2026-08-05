package com.sky.service;

import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

public interface SetMealService {

    /**
     * 根据套餐id查询
     * @param id
     */
    SetmealVO getBySetMealId(Long id);

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
}
