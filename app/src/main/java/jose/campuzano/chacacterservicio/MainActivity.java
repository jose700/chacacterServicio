package jose.campuzano.chacacterservicio;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jose.campuzano.chacacterservicio.adapters.charactersAdapter;
import jose.campuzano.chacacterservicio.models.characters;
import jose.campuzano.chacacterservicio.viewsmodels.MovieListViewModel;

public class MainActivity extends AppCompatActivity {
    private MovieListViewModel movieListViewModel;
    private static final String CHARACTERS_URL = "https://thronesapi.com/api/v2/Characters";
    List<characters> moviesList;

    RecyclerView Rc_movies, rv_movies_week;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        // initialize ids
        iniViews();
        ObserverAnyChance();
        
        // initialize view model
        GetData getData = new GetData();
        getData.execute();
    }

    private void ObserverAnyChance() {
        characters[] movies = new characters[0];
        for(characters movie:movies) {
            movie.getFirstName();
            movie.getFullName();
            Log.e( TAG, "datos: " + movie.getTitle() );

        }
    }
    @SuppressLint("CutPasteId")
    private void iniViews() {
        moviesList = new ArrayList<>();
        //todos las peliculas recomendadas
        Rc_movies = findViewById( R.id.Rc_movies );
    }
    //get data getData
    @SuppressLint("StaticFieldLeak")
    public class GetData extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder current = new StringBuilder();
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL( CHARACTERS_URL );
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader( is );

                    int data = isr.read();
                    while (data != -1) {
                        current.append( (char) data );
                        data = isr.read();
                    }
                    return current.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject json = new JSONObject( s );
                JSONArray jsonArray = json.getJSONArray("results");
                for(int i =0; i<jsonArray.length();i++){
                    JSONObject json1 = jsonArray.getJSONObject(i);
                    characters model = new characters();
                    model.setId( Integer.parseInt( json1.getString( "id" ) ) );
                    model.setFirstName( json1.getString("name") );
                    model.setLastName( json1.getString("lastName") );
                    model.setFullName( json1.getString("FullName") );
                    model.setFirstName( json1.getString("FirstName") );
                    model.setTitle( json1.getString( "title" ) );
                    model.setImage( json1.getString( "poster_path" ) );
                    model.setImageUrl( json1.getString( "imageUrl" ) );
                    moviesList.add(model);
                    
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PutDataIntoRecycleSeries(moviesList);
        }

    }
    private void PutDataIntoRecycleSeries(List<characters> moviesList) {
        charactersAdapter adapter = new charactersAdapter( this, moviesList );
        Rc_movies.setLayoutManager( (new GridLayoutManager(this,3)) );
        Rc_movies.setAdapter(adapter);
    }

}