package jose.campuzano.chacacterservicio.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import jose.campuzano.chacacterservicio.models.characters;
import jose.campuzano.chacacterservicio.request.MovieApiClient;

public class CharactersRepository {
    //this class is used for Repository
    private static CharactersRepository instance;

    private MovieApiClient movieApiClient;
    //Live Data
    public static CharactersRepository getInstance(){
        if(instance == null){
            instance = new CharactersRepository();
        }
        return instance;
    }
    private CharactersRepository(){
        movieApiClient = MovieApiClient.getInstance();
        //movieList= new MutableLiveData<>();
        //movieList.setValue(null);
    }
    public LiveData<List<characters>> getMovieList(){
        return movieApiClient.getMovieList();
    }

    //2.-calling the method
    public void serachMovieApi(String query, int pageNumber){
        movieApiClient.searchMoviesApi(query,pageNumber);

    }

}
