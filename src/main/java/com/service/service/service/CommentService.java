package com.service.service.service;

import com.service.service.controller.req.CommentReq;
import com.service.service.controller.resp.Comment;
import java.util.List;

public interface CommentService {

  Comment add(CommentReq req);

  List<Comment> list(Long articleId);
}
