package com.service.service.service.impl;

import com.service.service.controller.resp.Tag;
import com.service.service.mapper.TagMapper;
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
}
