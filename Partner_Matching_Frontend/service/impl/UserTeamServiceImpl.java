package com.rgsj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgsj.mapper.UserTeamMapper;
import com.rgsj.model.domain.UserTeam;
import com.rgsj.service.UserTeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
        implements UserTeamService {
    @Resource
    private UserTeamMapper userTeamMapper;

    @Override
    public List<Long> getUserIdsByTeamId(Long teamId) {
        // 查询出符合条件的UserTeam实体类列表
        List<UserTeam> userTeamList = userTeamMapper.selectUserIdsByTeamId(teamId);
        // 将UserTeam实体类列表转换为用户ID列表
        List<Long> userIds = userTeamList.stream().map(UserTeam::getUserId).collect(Collectors.toList());
        return userIds;
    }

}




