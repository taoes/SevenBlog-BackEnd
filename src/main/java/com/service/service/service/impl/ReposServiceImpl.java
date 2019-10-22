package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.service.controller.resp.Repos;
import com.service.service.mapper.ReposMapper;
import com.service.service.mapper.dao.ReposDO;
import com.service.service.service.ReposService;
import com.service.service.service.converter.ReposConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReposServiceImpl implements ReposService {

    @Autowired
    private ReposMapper reposMapper;

    @Override
    public List<Repos> getAllList(int size) {


        Wrapper<ReposDO> query = new LambdaQueryWrapper<ReposDO>()
                .orderByDesc(ReposDO::getId);
        return reposMapper.selectPage(new Page<>(1, size), query).getRecords().stream().map(ReposConverter::of).collect(Collectors.toList());
    }
}

