package com.hussler.quickcut.service;

import com.hussler.quickcut.entity.Article;
import com.hussler.quickcut.entity.User;
import com.hussler.quickcut.entity.UserBookmark;
import com.hussler.quickcut.repository.ArticleRepository;
import com.hussler.quickcut.repository.UserBookmarkRepository;
import com.hussler.quickcut.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final UserBookmarkRepository userBookmarkRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    // Hardcoded user ID for "No Auth" scenario
    private static final Long DEFAULT_USER_ID = 1L;

    public List<UserBookmark> getBookmarks() {
        return userBookmarkRepository.findByUserId(DEFAULT_USER_ID);
    }

    @Transactional
    public UserBookmark addBookmark(Long articleId, String note) {
        // Ensure user exists (create dummy if needed for this demo)
        User user = userRepository.findById(DEFAULT_USER_ID)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setId(DEFAULT_USER_ID);
                    newUser.setEmail("demo@example.com");
                    newUser.setHashedPassword("dummy");
                    newUser.setRole("reader");
                    return userRepository.save(newUser);
                });

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        Optional<UserBookmark> existing = userBookmarkRepository.findByUserIdAndArticleId(DEFAULT_USER_ID, articleId);
        if (existing.isPresent()) {
            return existing.get();
        }

        UserBookmark bookmark = new UserBookmark();
        bookmark.setUser(user);
        bookmark.setArticle(article);
        bookmark.setNote(note);

        return userBookmarkRepository.save(bookmark);
    }

    @Transactional
    public void removeBookmark(Long articleId) {
        userBookmarkRepository.deleteByUserIdAndArticleId(DEFAULT_USER_ID, articleId);
    }
}
