package com.btea.wxgoutmanage.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btea.wxgoutmanage.dao.entity.MedicineDO;
import com.btea.wxgoutmanage.dto.req.QueryMedicineByCategoryReqDTO;
import com.btea.wxgoutmanage.dto.req.QueryMedicineReqDTO;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineByCategoryRespVO;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineRespVO;

import java.util.List;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 17:29
 * @Description: 药品接口层
 */
public interface MedicineService extends IService<MedicineDO> {


    /**
     * 根据类别分页查询药品
     *
     * @return 所有药品
     */
    List<QueryMedicineByCategoryRespVO> getMedicineByCategory(QueryMedicineByCategoryReqDTO requestParam);

    /**
     * 模糊查询药品
     *
     * @param requestParam 模糊查询参数
     * @return 模糊查询结果
     */
    List<QueryMedicineRespVO> fuzzyQueryMedicine(QueryMedicineReqDTO requestParam);
}
