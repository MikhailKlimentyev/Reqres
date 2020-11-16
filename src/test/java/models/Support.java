package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Support {

    @Expose
    private String url;
    @Expose
    private String text;
}
