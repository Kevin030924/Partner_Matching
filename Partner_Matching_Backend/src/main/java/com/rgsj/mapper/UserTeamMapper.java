package com.rgsj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rgsj.model.domain.UserTeam;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface UserTeamMapper extends BaseMapper<UserTeam> {

    List<UserTeam> selectUserIdsByTeamId(Long teamId);
}




