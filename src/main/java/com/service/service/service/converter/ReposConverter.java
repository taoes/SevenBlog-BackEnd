package com.service.service.service.converter;

import com.service.service.controller.resp.Repos;
import com.service.service.mapper.dao.ReposDO;

public class ReposConverter implements Converter {

    public static Repos of(ReposDO reposDO) {
        return new Repos()
                .setId(reposDO.getId())
                .setName(reposDO.getName())
                .setUrl(reposDO.getUrl())
                .setDesc(reposDO.getDesc());
    }
}
