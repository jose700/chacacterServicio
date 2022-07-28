package jose.campuzano.chacacterservicio.request;








import jose.campuzano.chacacterservicio.interfaces.ChacartersApi;
import okhttp3.Credentials;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static Object Credentials;
    // retrofit
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl( (String) Credentials )
            .addConverterFactory( GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ChacartersApi movieApi = retrofit.create( ChacartersApi.class );

    public static ChacartersApi getMovieApi() {
        return movieApi;
    }

}
