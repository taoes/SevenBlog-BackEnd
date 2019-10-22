package com.service.service.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.service.controller.resp.Blog;
import com.service.service.controller.resp.PageInfo;
import com.service.service.controller.resp.Tag;
import com.service.service.mapper.BlogMapper;
import com.service.service.mapper.dao.BlogDO;
import com.service.service.service.BlogService;
import com.service.service.service.TagService;
import com.service.service.service.converter.BlogConverter;
import com.service.service.service.converter.JsonConverter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    TagService tagService;

    @Override
    public PageInfo<Blog> list(String searchKey, long pageNun, long pageSize, String blogType) {

        Wrapper<BlogDO> queryWrapper = new LambdaQueryWrapper<BlogDO>()
                .eq(StringUtils.hasText(blogType), BlogDO::getType, blogType)
                .orderByDesc(BlogDO::getId);
        IPage<BlogDO> blogDOIPage = blogMapper.selectPage(new Page<>(pageNun, pageSize), queryWrapper);


        List<BlogDO> records = blogDOIPage.getRecords();
        Set<Long> allTagId = records.stream().map(BlogDO::getTagIds).filter(Objects::nonNull).map(str -> JsonConverter.readListValue(str, Long.class)).flatMap(List::stream)
                .collect(Collectors.toSet());
        Map<Long, Tag> tagMap = tagService.getByIds(allTagId).stream().filter(Objects::nonNull).collect(Collectors.toMap(Tag::getId, Function.identity()));


        Collection<Blog> data = records
                .stream().map(blogDO -> {
                    Blog blog = BlogConverter.of(blogDO);
                    List<Long> tagIds = JsonConverter.readListValue(blogDO.getTagIds(), Long.class);
                    List<Tag> tagList = new ArrayList<>(tagIds.size());
                    tagMap.forEach((key, value) -> {
                        if (tagIds.contains(key)) {
                            tagList.add(value);
                        }
                    });
                    return blog.setTags(tagList);

                }).collect(Collectors.toList());


        return new PageInfo<Blog>()
                .setData(data)
                .setTotal(blogDOIPage.getTotal())
                .setPage(blogDOIPage.getSize());
    }

    @Override
    public Blog getById(Long blogId) {
        BlogDO blogDO = blogMapper.selectById(blogId);
        if (blogDO == null) {
            throw new RuntimeException("文章不存在，获取失败");
        }

        Blog blog
                = BlogConverter.of(blogDO);

        List<Long> tagIds = JsonConverter.readListValue(blogDO.getTagIds(), Long.class);
        List<Tag> tagList = tagService.getByIds(tagIds);
        return blog.setTags(tagList);
    }
}
