package com.NovelBookOnline.NovelBookOnline.Helper;

import com.NovelBookOnline.NovelBookOnline.Entity.*;

import java.util.Collection;
import java.util.List;

public class CountHelper {


    // Count reading
    public static int countTotalReadingNovelsOfAuthor(Author author) {
        return  author.getNovels().stream()
                .map(Novel::getChapters
                ).flatMap(Collection::stream)
                .map(Chapter::getDailyReads)
                .flatMap(Collection::stream)
                .map(DailyRead::getReadCount)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int countTotalReadingOfNovel(Novel novel) {
        return novel.getChapters().stream()
                .map(Chapter::getDailyReads)
                .flatMap(Collection::stream)
                .map(DailyRead::getReadCount)
                .mapToInt(Integer::intValue)
                .sum();
    }

    // count Like
    public static int countTotalLikeOfNovel(Novel novel) {
        return novel.getChapters()
                .stream()
                .map(Chapter::getLikes)
                .map(List::size)
                .reduce(0, Integer::sum);
    }

    // count comment
    public static int countSubComment(Comment comment) {
       return comment.getReplies().stream()
               .filter(c -> c.getDeletedAt() != null)
               .toList().size();
    }



}
