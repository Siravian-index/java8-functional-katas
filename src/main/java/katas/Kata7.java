package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();


        return movieLists.stream()
                .map(movieList -> movieList.getVideos())
                .flatMap(movies -> movies.stream())
                .map(movie -> {
                    BoxArt smallestBoxArt = movie.getBoxarts().stream()
                            .reduce((boxArt, boxArt2) -> boxArt.getHeight() * boxArt.getWidth() > boxArt2.getWidth() * boxArt2.getHeight() ? boxArt2 : boxArt).orElseThrow();
                    HashMap<String, String> mapResult = new HashMap<>();
                    mapResult.put("id", movie.getId().toString());
                    mapResult.put("title", movie.getTitle());
                    mapResult.put("boxart", smallestBoxArt.getUrl());
                    return mapResult;
                }).collect(Collectors.toList());

//        return ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys", "boxart", "url"));
    }
}
