package com.btea.wxgoutmanage.server;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.btea.wxgoutmanage.dao.entity.MedicineDO;
import com.btea.wxgoutmanage.dto.req.QueryMedicineByCategoryReqDTO;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineByCategoryRespVO;

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
    Page<QueryMedicineByCategoryRespVO> getMedicineByCategoryPage(QueryMedicineByCategoryReqDTO requestParam);
}
