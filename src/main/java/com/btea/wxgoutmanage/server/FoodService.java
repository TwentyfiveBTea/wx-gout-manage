package com.btea.wxgoutmanage.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btea.wxgoutmanage.dao.entity.FoodDO;
import com.btea.wxgoutmanage.dto.req.QueryFoodByCategoryReqDTO;
import com.btea.wxgoutmanage.dto.req.QueryFoodReqDTO;
import com.btea.wxgoutmanage.vo.resp.QueryFoodByCategoryRespVO;
import com.btea.wxgoutmanage.vo.resp.QueryFoodRespVO;

import java.util.List;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/17 14:07
 * @Description: 食物接口层
 */
public interface FoodService extends IService<FoodDO> {

    /**
     * 根据类别查询食物
     *
     * @param requestParam 查询参数
     * @return List<QueryFoodByCategoryRespVO> 返回查询结果
     */
    List<QueryFoodByCategoryRespVO> getFoodByCategory(QueryFoodByCategoryReqDTO requestParam);


    /**
     * 模糊查询食物
     *
     * @param requestParam 模糊查询参数
     * @return List<QueryFoodRespVO> 模糊查询结果
     */
    List<QueryFoodRespVO> fuzzyQueryFood(QueryFoodReqDTO requestParam);
}
