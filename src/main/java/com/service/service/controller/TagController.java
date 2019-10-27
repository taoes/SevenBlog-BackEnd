package com.service.service.controller;


import com.service.service.controller.resp.Tag;
import com.service.service.service.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {


    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> list() {
        return tagService.all();
    }


    @PostMapping
    public Tag add(@RequestBody Tag tag) {
        return tagService.add(tag.getName(), tag.getType());
    }


    @PatchMapping("/{tagId}")
    public Tag getById(@PathVariable("tagId") Long tagId, @RequestBody Tag tag) {
        return tagService.update(tag.setId(tagId));
    }
}
