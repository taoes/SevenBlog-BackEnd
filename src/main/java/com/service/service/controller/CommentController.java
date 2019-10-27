package com.service.service.controller;

import com.service.service.controller.req.CommentReq;
import com.service.service.controller.resp.Comment;
import com.service.service.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired private CommentService commentService;

  @PostMapping
  public Comment create(@RequestBody CommentReq req) {
    return commentService.add(req);
  }

  @GetMapping
  public List<Comment> getAllByArticleId(@RequestParam Long articleId) {
    return commentService.list(articleId);
  }
}
