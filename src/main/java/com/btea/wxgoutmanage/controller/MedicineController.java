package com.btea.wxgoutmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.btea.wxgoutmanage.common.convention.result.Result;
import com.btea.wxgoutmanage.common.convention.result.Results;
import com.btea.wxgoutmanage.dto.req.QueryMedicineByCategoryReqDTO;
import com.btea.wxgoutmanage.server.MedicineService;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineByCategoryRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 17:22
 * @Description: 药品控制类
 */
@RestController
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping("/medicine/page")
    public Result<Page<QueryMedicineByCategoryRespVO>> getMedicineByCategoryPage(@RequestBody QueryMedicineByCategoryReqDTO requestParam) {
        return Results.success(medicineService.getMedicineByCategoryPage(requestParam));
    }

}
