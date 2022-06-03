package ucs.android.aulas.trabalho02_v2.REST;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ucs.android.aulas.trabalho02_v2.DAO.BDSQLiteHelper;
import ucs.android.aulas.trabalho02_v2.model.Json;

public interface AppInterface {
    @GET("json")
    Call<List<Json>> json();



}
