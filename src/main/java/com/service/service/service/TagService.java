package com.service.service.service;

import com.service.service.controller.resp.Tag;
import java.util.Collection;
import java.util.List;

public interface TagService {

    List<Tag> getByIds(Collection<Long> ids);


    Tag getById(Long id);
}
