package com.hussler.quickcut.service;

import com.hussler.quickcut.entity.ArticleGnews;
import com.hussler.quickcut.repository.ArticleGnewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleGnewsService {

    private final ArticleGnewsRepository articleGnewsRepository;

    public List<ArticleGnews> getAllGnewsArticles() {
        return articleGnewsRepository.findAll();
    }
}
