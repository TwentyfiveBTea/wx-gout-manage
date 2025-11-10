package com.btea.wxgoutmanage.controller;

import com.btea.wxgoutmanage.common.convention.result.Result;
import com.btea.wxgoutmanage.common.convention.result.Results;
import com.btea.wxgoutmanage.server.MedicineService;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineByCategoryRespVO;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Result<List<QueryMedicineByCategoryRespVO>> getMedicineByCategory(@RequestParam("medicineCategory") String medicineCategory) {
        return Results.success(medicineService.getMedicineByCategory(medicineCategory));
    }

    @GetMapping("/medicine/fuzzy-query")
    public Result<List<QueryMedicineRespVO>> fuzzyQueryMedicine(@RequestParam("medicineName") String medicineName) {
        return Results.success(medicineService.fuzzyQueryMedicine(medicineName));
    }

}
