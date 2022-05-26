package katas;

import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .map(movieList -> movieList.getVideos())
                .flatMap(movies -> movies.stream())
                .map(movie -> {
                    BoxArt boxArt1 = movie.getBoxarts().stream()
                            .filter(boxArt -> boxArt.getWidth() == 150 && boxArt.getHeight() == 200)
                            .findFirst()
                            .orElseThrow();

                    HashMap<String, String> map = new HashMap<>();
                    map.put("title", movie.getTitle());
                    map.put("id", movie.getId().toString());
                    map.put("boxart", boxArt1.getUrl());
                    return map;
                }).collect(Collectors.toList());
    }
}
