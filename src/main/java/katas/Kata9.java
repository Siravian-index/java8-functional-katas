package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        return movieLists.stream()
                .map(movieList -> movieList.getVideos())
                .flatMap(movies -> movies.stream())
                .map(movie -> {
                    InterestingMoment middle = movie.getInterestingMoments().stream()
                            .filter(interestingMoment -> interestingMoment.getType().equals("Middle"))
                            .findFirst()
                            .orElseThrow();
                    BoxArt smallestBoxArt = movie.getBoxarts().stream()
                            .reduce((boxArt, boxArt2) -> boxArt.getHeight() * boxArt.getWidth() > boxArt2.getWidth() * boxArt2.getHeight() ? boxArt2 : boxArt).orElseThrow();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("id", movie.getId().toString());
                    map.put("title", movie.getTitle());
                    map.put("time", middle.getTime().toString());
                    map.put("url", smallestBoxArt.getUrl());
                    return map;
                }).collect(Collectors.toList());
    }
}
