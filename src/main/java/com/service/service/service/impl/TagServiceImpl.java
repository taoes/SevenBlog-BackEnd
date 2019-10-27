package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.service.controller.resp.Tag;
import com.service.service.mapper.TagMapper;
import com.service.service.mapper.dao.TagDO;
import com.service.service.service.TagService;
import com.service.service.service.converter.TagConverter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> all() {
        Wrapper<TagDO> queryWrapper =
                new LambdaQueryWrapper<TagDO>()
                .orderByDesc(TagDO::getId);
        return tagMapper.selectList(queryWrapper).stream().map(TagConverter::of).collect(Collectors.toList());
    }

    @Override
    public Tag add(String name, String type) {
        TagDO entity = new TagDO();
        entity.setName(name).setType(type);
        tagMapper.insert(entity);
        return getById(entity.getId());
    }

    @Override
    public List<Tag> getByIds(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return tagMapper.selectBatchIds(ids).stream().map(TagConverter::of).collect(Collectors.toList());
    }

    @Override
    public Tag getById(Long id) {
        return Optional.ofNullable(tagMapper.selectById(id)).map(TagConverter::of).orElse(null);
    }

    @Override
    public Tag update(Tag tag) {
        TagDO tagDO = new TagDO().setId(tag.getId()).setName(tag.getName()).setType(tag.getType());
        tagMapper.updateById(tagDO);
        return getById(tag.getId());
    }
}
