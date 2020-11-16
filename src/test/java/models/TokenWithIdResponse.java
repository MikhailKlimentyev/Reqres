package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class TokenWithIdResponse {

    @Expose
    private int id;
    @Expose
    private String token;
}
