package ucs.android.aulas.trabalho02_v2.REST;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//    public static final String BASE_URL = "https://viacep.com.br/ws/RS/Caxias do Sul/Domingos/";
//    private static Retrofit retrofit = null;


    public static Retrofit getClient(String BASE_URL, Retrofit retrofit) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
