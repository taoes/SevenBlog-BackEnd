package com.service.service.controller;

import com.service.service.controller.resp.Repos;
import com.service.service.service.ReposService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github/repos")
public class ReposController {

    @Autowired
    private ReposService reposService;


    @GetMapping
    public List<Repos> getListWithTop(@RequestParam int size) {
        return reposService.getAllList(size);
    }
}
