package com.btea.wxgoutmanage.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btea.wxgoutmanage.dao.entity.MedicineDO;
import com.btea.wxgoutmanage.dao.mapper.MedicineMapper;
import com.btea.wxgoutmanage.dto.req.QueryMedicineByCategoryReqDTO;
import com.btea.wxgoutmanage.server.MedicineService;
import com.btea.wxgoutmanage.vo.resp.MedicineRespVO;
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
    public Page<MedicineRespVO> getMedicineByCategoryPage(QueryMedicineByCategoryReqDTO requestParam) {
        Page<MedicineDO> page = new Page<>(requestParam.getCurrent(), requestParam.getSize());
        LambdaQueryWrapper<MedicineDO> queryWrapper = Wrappers.lambdaQuery(MedicineDO.class)
                .eq(MedicineDO::getMedicineCategory, requestParam.getMedicineCategory());
        Page<MedicineDO> queryPage = baseMapper.selectPage(page, queryWrapper);
        Page<MedicineRespVO> voPage = new Page<>(queryPage.getCurrent(), queryPage.getSize(), queryPage.getTotal());
        voPage.setPages(queryPage.getPages());
        List<MedicineRespVO> voList = queryPage.getRecords().stream()
                .map(medicineDO -> MedicineRespVO.builder()
                        .medicineName(medicineDO.getMedicineName())
                        .description(medicineDO.getDescription())
                        .descriptionImageUrl(medicineDO.getDescriptionImageUrl())
                        .build())
                .collect(Collectors.toList());
        voPage.setRecords(voList);
        return voPage;
    }
}
