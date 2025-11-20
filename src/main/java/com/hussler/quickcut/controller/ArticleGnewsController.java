package com.hussler.quickcut.controller;

import com.hussler.quickcut.entity.ArticleGnews;
import com.hussler.quickcut.service.ArticleGnewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles-gnews")
@RequiredArgsConstructor
public class ArticleGnewsController {

    private final ArticleGnewsService articleGnewsService;

    @GetMapping
    public List<ArticleGnews> getAllGnewsArticles() {
        return articleGnewsService.getAllGnewsArticles();
    }
}
