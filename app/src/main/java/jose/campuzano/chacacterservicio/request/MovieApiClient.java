package jose.campuzano.chacacterservicio.request;

import android.net.Credentials;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jose.campuzano.chacacterservicio.models.characters;
import jose.campuzano.chacacterservicio.response.MovieSearchResponse;
import jose.campuzano.chacacterservicio.ui.AppExecutors;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    //Live Data
    private MutableLiveData<List<characters>> movieList;

    private static MovieApiClient instance;

    //making global RUNNABLE
    private RetreiveMoviesRunnable retreiveMoviesRunnable;

    public static MovieApiClient getInstance(){
        if(instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }
    private MovieApiClient(){
        movieList = new MutableLiveData<>();
        //movieList.setValue(null);
    }
    public LiveData<List<characters>> getMovieList(){
        return movieList;
    }

    //1.-this methods that we are going to call through the classess
    public void searchMoviesApi(String query, int pageNumber){
        if(retreiveMoviesRunnable !=null){
            retreiveMoviesRunnable = null;
        }
        retreiveMoviesRunnable = new RetreiveMoviesRunnable( query, pageNumber );

        final Future  myHandler = AppExecutors.getInstance().schedulerExecutorService().submit(retreiveMoviesRunnable);
        AppExecutors.getInstance().schedulerExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //cancel task
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);

    }
    //Retreiving data from RestApi by runnable class
    //We have 2 types query : th Id & search
    private class RetreiveMoviesRunnable implements Runnable{

        private String  query;
        private int pageNumber;
        boolean cancelRequest;

        public RetreiveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //getting the response objects
            try{
                Response response = getMovies(query, pageNumber).execute();
                if(cancelRequest){
                    return ;
                }
                if(response.code()==200){
                    List<characters>list  = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber ==1){
                        //sending data to live data
                        movieList.postValue( list );
                    }else{
                        List<characters> currentMovies = movieList.getValue();
                        currentMovies.addAll( list );
                        movieList.postValue(currentMovies);
                    }
                }
                else{
                    String error = response.errorBody().string();
                    Log.v("Tag","error"+ error);
                    movieList.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                movieList.postValue(null);
            }
        }
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
            return Service.getMovieApi().searchMovie(
                    Credentials.class.getSimpleName(),
                    query,
                    pageNumber
            );
        }
        private void cancelRequest(){
            Log.v("Tag","Cancel search");
            cancelRequest = true;
        }
    }
}
