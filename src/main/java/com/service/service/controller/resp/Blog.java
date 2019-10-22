package com.service.service.controller.resp;

import java.util.List;
import lombok.Data;

@Data
public class Blog {

    private long id;

    private String title;

    private String description;

    private List<Tag> tags;

    private String content;
}
