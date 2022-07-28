package jose.campuzano.chacacterservicio.viewsmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;



import java.util.List;

import jose.campuzano.chacacterservicio.models.characters;
import jose.campuzano.chacacterservicio.repositories.CharactersRepository;

public class MovieListViewModel extends ViewModel {
    // this class is used for ViewModel


    private CharactersRepository movieRepository;

    //constructor

    public MovieListViewModel(){
        movieRepository = CharactersRepository.getInstance();
    }
    public LiveData<List<characters>> getMovieList(){
        return movieRepository.getMovieList();
    }

    //3.-Calling method in viewModel
    public void searchMovieApi(String query, int pageNumber){
        movieRepository.serachMovieApi(query,pageNumber);
    }
}
