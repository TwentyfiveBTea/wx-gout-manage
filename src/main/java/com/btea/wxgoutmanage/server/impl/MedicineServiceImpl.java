package com.btea.wxgoutmanage.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btea.wxgoutmanage.dao.entity.MedicineDO;
import com.btea.wxgoutmanage.dao.mapper.MedicineMapper;
import com.btea.wxgoutmanage.dto.req.QueryMedicineByCategoryReqDTO;
import com.btea.wxgoutmanage.dto.req.QueryMedicineReqDTO;
import com.btea.wxgoutmanage.server.MedicineService;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineByCategoryRespVO;
import com.btea.wxgoutmanage.vo.resp.QueryMedicineRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 17:30
 * @Description: 药品接口实现类
 */
@Service
@RequiredArgsConstructor
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, MedicineDO> implements MedicineService {

    private final MedicineMapper medicineMapper;

    /**
     * 根据类别分页查询药品
     *
     * @param requestParam 请求参数
     * @return Page<MedicineRespVO> 返回分页数据
     */
    @Override
    public List<QueryMedicineByCategoryRespVO> getMedicineByCategory(QueryMedicineByCategoryReqDTO requestParam) {
        LambdaQueryWrapper<MedicineDO> queryWrapper = Wrappers.lambdaQuery(MedicineDO.class)
                .eq(MedicineDO::getMedicineCategory, requestParam.getMedicineCategory());
        return baseMapper.selectList(queryWrapper).stream()
                .map(medicineDO -> QueryMedicineByCategoryRespVO.builder()
                        .medicineName(medicineDO.getMedicineName())
                        .description(medicineDO.getDescription())
                        .medicineImageUrl(medicineDO.getMedicineImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 模糊查询药品
     *
     * @param requestParam 模糊查询参数
     * @return List<QueryMedicineRespVO> 返回查询结果
     */
    @Override
    public List<QueryMedicineRespVO> fuzzyQueryMedicine(QueryMedicineReqDTO requestParam) {
        LambdaQueryWrapper<MedicineDO> queryWrapper = Wrappers.lambdaQuery(MedicineDO.class)
                .like(MedicineDO::getMedicineName, requestParam.getMedicineName());
        List<MedicineDO> medicineDOList = baseMapper.selectList(queryWrapper);
        return medicineDOList.stream()
                .map(medicineDO -> QueryMedicineRespVO.builder()
                        .medicineName(medicineDO.getMedicineName())
                        .medicineCategory(medicineDO.getMedicineCategory())
                        .description(medicineDO.getDescription())
                        .medicineImageUrl(medicineDO.getMedicineImageUrl())
                        .build())
                .collect(Collectors.toList());
    }
}
