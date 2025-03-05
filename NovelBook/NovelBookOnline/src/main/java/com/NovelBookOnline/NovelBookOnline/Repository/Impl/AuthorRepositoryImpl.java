package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Author;
import com.NovelBookOnline.NovelBookOnline.Repository.IAuthorRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.AuthorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements IAuthorRepository {
    private final AuthorJpaRepository authorJpaRepository;

    @Override
    public Author getAuthor(String id) {
        return authorJpaRepository.getAuthor(id).orElseThrow();
    }

    @Override
    public Page<Author> getAllAuthors(String sortOrder, Pageable pageable) {
        List<String> authorIds = authorJpaRepository.getAllAuthorIds(sortOrder);
        return authorJpaRepository.getAllAuthorsByIds(authorIds, pageable);
    }

    @Override
    public Page<Author> getTopAuthorByLike(String sortOrder, Pageable pageable) {
        List<String> authorIds = authorJpaRepository.getAllAuthorIds(sortOrder);
        return authorJpaRepository.getAllAuthorsByIds(authorIds, pageable);
    }

    @Override
    public Page<Author> findAuthorsByKeyWord(String keyword, Pageable pageable) {
        return authorJpaRepository.findAuthorsByKeyWord(keyword, pageable);
    }

    @Override
    public void save(Author author) {
        authorJpaRepository.save(author);
    }

    @Override
    public void delete(Author author) {
        author.setDeletedAt(LocalDateTime.now());
        authorJpaRepository.save(author);
    }
}
