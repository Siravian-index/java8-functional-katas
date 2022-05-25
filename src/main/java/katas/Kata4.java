package katas;

import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream().flatMap(movieList -> movieList.getVideos().stream().map(movie -> {
            HashMap<String, String> map = new HashMap<>();
            List<BoxArt> boxArtList = movie.getBoxarts().stream()
                    .filter(boxArt -> boxArt.getWidth() == 150 && boxArt.getHeight() == 200)
                    .collect(Collectors.toList());
            map.put("id", movie.getId().toString());
            map.put("title", movie.getTitle());
            map.put("boxart", boxArtList.get(0).getUrl());
            return map;
        })).collect(Collectors.toList());
    }
}
