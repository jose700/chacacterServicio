package jose.campuzano.chacacterservicio.interfaces;

import jose.campuzano.chacacterservicio.models.characters;
import jose.campuzano.chacacterservicio.response.MovieSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChacartersApi {

    //Search for movies
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String Key,
            @Query("query") String query,
            @Query("page") int pageNumber
    );
    @GET("3/movie/{movie_id}?")
    Call<characters> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String Key
    );
}
