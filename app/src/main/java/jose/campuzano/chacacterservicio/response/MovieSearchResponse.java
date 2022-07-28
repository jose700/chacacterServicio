package jose.campuzano.chacacterservicio.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import jose.campuzano.chacacterservicio.models.characters;

// this class is for getting multiple movies (Movie list) -popular, top rated, upcoming
public class MovieSearchResponse {
    @SerializedName("results")
    @Expose
   private int total_results;

    @SerializedName("results")
    @Expose
    private List<characters> movies;

    public int getTotal_results() {
        return total_results;

    }
    public List<characters> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_results=" + total_results +
                ", movies=" + movies +
                '}';
    }
}
