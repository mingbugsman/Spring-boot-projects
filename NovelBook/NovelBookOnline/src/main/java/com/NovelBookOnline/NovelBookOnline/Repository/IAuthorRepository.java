package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAuthorRepository {
    Author getAuthor(String id);
    Page<Author> getAllAuthors(String sortOrder, Pageable pageable);
    Page<Author> getAuthorsByKeyWord(String keyword, Pageable pageable);
    void save(Author author);
    void delete(Author author);
}
