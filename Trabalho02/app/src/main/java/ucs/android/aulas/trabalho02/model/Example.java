package ucs.android.aulas.trabalho02.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("powerstats")
@Expose
private Powerstats powerstats;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Powerstats getPowerstats() {
return powerstats;
}

public void setPowerstats(Powerstats powerstats) {
this.powerstats = powerstats;
}

public Example(String id, String name, Powerstats powerstats) {
        super();
        this.id = id;
        this.name = name;
        this.powerstats = powerstats;
}

}