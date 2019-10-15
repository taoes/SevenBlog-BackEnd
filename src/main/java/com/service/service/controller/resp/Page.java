package com.service.service.controller.resp;

import java.util.Collection;
import lombok.Data;

@Data
public class Page<T> {

    private int total;

    private int page;


    private Collection<T> data;
}
