package com.btea.wxgoutmanage.controller;

import com.btea.wxgoutmanage.common.convention.result.Result;
import com.btea.wxgoutmanage.common.convention.result.Results;
import com.btea.wxgoutmanage.dto.req.QueryMedicineByCategoryReqDTO;
import com.btea.wxgoutmanage.dto.req.QueryMedicineReqDTO;
import com.btea.wxgoutmanage.server.MedicineService;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineByCategoryRespVO;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 17:22
 * @Description: 药品控制类
 */
@RestController
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping("/medicine/query")
    public Result<List<QueryMedicineByCategoryRespVO>> getMedicineByCategory(@RequestBody QueryMedicineByCategoryReqDTO requestParam) {
        return Results.success(medicineService.getMedicineByCategory(requestParam));
    }

    @GetMapping("/medicine/fuzzy-query")
    public Result<List<QueryMedicineRespVO>> fuzzyQueryMedicine(@RequestBody QueryMedicineReqDTO requestParam) {
        return Results.success(medicineService.fuzzyQueryMedicine(requestParam));
    }

}
