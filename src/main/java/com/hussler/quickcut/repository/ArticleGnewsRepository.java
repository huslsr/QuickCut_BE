package com.hussler.quickcut.repository;

import com.hussler.quickcut.entity.ArticleGnews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleGnewsRepository extends JpaRepository<ArticleGnews, Long> {
}
