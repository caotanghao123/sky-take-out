package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
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
    public SetmealVO getBySetMealIdReturnSetmealVO(Long id) {

        SetmealVO setMealVO = setmealMapper.getBySetMealIdReturnSetmealVO(id);
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

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 批量删除
     * @param ids
     */
    // TODO 本处还是一个不能删就全不能删的问题
    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
        //判断是否在售
        ids.forEach(id ->{
            Integer status = setmealMapper.getById(id).getStatus();
            if(status == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
        //删除套餐基本信息
        setmealMapper.deleteBatch(ids);
        //删除套餐的菜品信息（setmeal_dish）
        setmealDishMapper.deleteBatch(ids);
    }
}
