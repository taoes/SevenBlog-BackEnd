package com.service.service.controller;


import com.service.service.controller.resp.Picture;
import com.service.service.mapper.dao.PictureType;
import com.service.service.service.PictureService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/type/{type}")
    public Map<String, List<Picture>> getPicture(@PathVariable("type") PictureType type) {
        return pictureService.getPictureList(type);
    }

}
