package com.service.service.controller;


import com.service.service.controller.resp.Blog;
import com.service.service.controller.resp.PageInfo;
import com.service.service.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @GetMapping
    public PageInfo<Blog> getAllBlog(@RequestParam(defaultValue = "1") long pageNumber, @RequestParam(defaultValue = "10") long pageSize, String blogType) {
        return blogService.list(null, pageNumber, pageSize,blogType);
    }

    @GetMapping("/detail/{blogId}")
    public Blog getById(@PathVariable("blogId") Long blogId) {
        return blogService.getById(blogId);
    }
}
