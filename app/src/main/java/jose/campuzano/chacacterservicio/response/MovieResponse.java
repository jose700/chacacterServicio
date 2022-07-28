package jose.campuzano.chacacterservicio.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import jose.campuzano.chacacterservicio.models.characters;

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private characters movie;
    public characters getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
