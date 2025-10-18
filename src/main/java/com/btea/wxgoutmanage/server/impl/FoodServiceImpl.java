package com.btea.wxgoutmanage.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btea.wxgoutmanage.dao.entity.FoodDO;
import com.btea.wxgoutmanage.dao.mapper.FoodMapper;
import com.btea.wxgoutmanage.dto.req.QueryFoodByCategoryReqDTO;
import com.btea.wxgoutmanage.dto.req.QueryFoodReqDTO;
import com.btea.wxgoutmanage.server.FoodService;
import com.btea.wxgoutmanage.vo.resp.QueryFoodByCategoryRespVO;
import com.btea.wxgoutmanage.vo.resp.QueryFoodRespVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/17 14:08
 * @Description: 食物接口层实现类
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, FoodDO> implements FoodService {

    /**
     * 根据类别查询食物
     *
     * @param requestParam 查询参数
     * @return List<QueryFoodByCategoryRespVO> 食物列表
     */
    @Override
    public List<QueryFoodByCategoryRespVO> getFoodByCategory(QueryFoodByCategoryReqDTO requestParam) {
        LambdaQueryWrapper<FoodDO> queryWrapper = Wrappers.lambdaQuery(FoodDO.class)
                .eq(FoodDO::getEdibleCategory, requestParam.getEdibleCategory());
        return baseMapper.selectList(queryWrapper).stream()
                .map(foodDO -> QueryFoodByCategoryRespVO.builder()
                        .foodName(foodDO.getFoodName())
                        .edibleCategory(foodDO.getEdibleCategory())
                        .foodImageUrl(foodDO.getFoodImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 模糊查询食物
     *
     * @param requestParam 模糊查询参数
     * @return List<QueryFoodRespVO> 食物列表
     */
    @Override
    public List<QueryFoodRespVO> fuzzyQueryFood(QueryFoodReqDTO requestParam) {
        LambdaQueryWrapper<FoodDO> queryWrapper = Wrappers.lambdaQuery(FoodDO.class)
                .like(FoodDO::getFoodName, requestParam.getFoodName());
        return baseMapper.selectList(queryWrapper).stream()
                .map(foodDO -> QueryFoodRespVO.builder()
                        .foodName(foodDO.getFoodName())
                        .purineContent(foodDO.getPurineContent())
                        .edibleCategory(foodDO.getEdibleCategory())
                        .foodImageUrl(foodDO.getFoodImageUrl())
                        .build())
                .collect(Collectors.toList());
    }


}
