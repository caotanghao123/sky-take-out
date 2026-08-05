package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Override
    public SetmealVO getBySetMealId(Long id) {

        SetmealVO setMealVO = setmealMapper.getBySetMealId(id);
        List<SetmealDish> setmealDishes = setmealDishMapper.getBySetmealId(id);
        setMealVO.setSetmealDishes(setmealDishes);
        return setMealVO;
    }

    /**
     * 新增套餐信息
      * @param setmealVO
     */
    @Override
    @Transactional
    public void save(SetmealVO setmealVO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealVO, setmeal);
        List<SetmealDish> setmealDishes = setmealVO.getSetmealDishes();
        //新增套餐基本信息
        setmealMapper.save(setmeal);
        //设置套餐id
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmeal.getId());
        });
        //新增套餐关联菜品信息
        setmealDishMapper.save(setmealDishes);
    }


}
