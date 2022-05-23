package ucs.android.aulas.trabalho02.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ucs.android.aulas.trabalho02.model.Example;

public interface ApiInterface {
    @GET("name")
    Call<List<Example>> superherois();
}
