package com.btea.wxgoutmanage.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btea.wxgoutmanage.dao.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 15:02
 * @Description: 用户持久层
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
