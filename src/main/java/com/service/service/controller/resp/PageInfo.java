package com.service.service.controller.resp;

import java.util.Collection;
import lombok.Data;

@Data
public class PageInfo<T> {

    private long total;

    private long page;


    private Collection<T> data;
}
