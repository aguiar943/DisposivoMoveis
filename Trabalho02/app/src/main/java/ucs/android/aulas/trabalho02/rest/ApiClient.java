package ucs.android.aulas.trabalho02.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://superheroapi.com/api/";
    public static final String Chave_Key = "10159885518644288";
    public static final String opcao = "/search/";
    private static Retrofit retrofit = null;
    private static final String URL = BASE_URL + Chave_Key + opcao;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
