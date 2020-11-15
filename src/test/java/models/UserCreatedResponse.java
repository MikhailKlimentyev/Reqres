package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UserCreatedResponse {

    @Expose
    private String id;
    @Expose
    private String createdAt;
}
