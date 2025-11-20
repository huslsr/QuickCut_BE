package com.hussler.quickcut.controller;

import com.hussler.quickcut.dto.BookmarkRequest;
import com.hussler.quickcut.entity.UserBookmark;
import com.hussler.quickcut.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public List<UserBookmark> getBookmarks() {
        return bookmarkService.getBookmarks();
    }

    @PostMapping
    public ResponseEntity<UserBookmark> addBookmark(@RequestBody BookmarkRequest request) {
        UserBookmark bookmark = bookmarkService.addBookmark(request.getArticleId(), request.getNote());
        return ResponseEntity.ok(bookmark);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> removeBookmark(@PathVariable Long articleId) {
        bookmarkService.removeBookmark(articleId);
        return ResponseEntity.ok().build();
    }
}
