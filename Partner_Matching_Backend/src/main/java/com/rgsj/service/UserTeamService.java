package com.rgsj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rgsj.model.domain.User;
import com.rgsj.model.domain.UserTeam;

import java.util.List;


public interface UserTeamService extends IService<UserTeam> {
    /**
     * 根据队伍ID查询对应的用户ID列表
     *
     * @param teamId 队伍ID
     * @return 用户ID列表
     */
    List<Long> getUserIdsByTeamId(Long teamId);
}
