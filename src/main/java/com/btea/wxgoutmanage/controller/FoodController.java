package com.btea.wxgoutmanage.controller;

import com.btea.wxgoutmanage.common.convention.result.Result;
import com.btea.wxgoutmanage.common.convention.result.Results;
import com.btea.wxgoutmanage.server.FoodService;
import com.btea.wxgoutmanage.vo.resp.QueryFoodByCategoryRespVO;
import com.btea.wxgoutmanage.vo.resp.QueryFoodRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/18 16:28
 * @Description: 食物控制类
 */
@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/food/query")
    public Result<List<QueryFoodByCategoryRespVO>> getFoodByCategory(@RequestParam("edibleCategory") String edibleCategory) {
        return Results.success(foodService.getFoodByCategory(edibleCategory));
    }

    @GetMapping("/food/fuzzy-query")
    public Result<List<QueryFoodRespVO>> fuzzyQueryFood(@RequestParam("foodName") String foodName) {
        return Results.success(foodService.fuzzyQueryFood(foodName));
    }

}
