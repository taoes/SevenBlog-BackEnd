package com.service.service.controller;


import com.service.service.controller.resp.Blog;
import com.service.service.controller.resp.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BlogController {


    @GetMapping
    public Page<Blog> getAllBlog(){

    }


}
