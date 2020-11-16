package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UserUpdatedResponse {

    @Expose
    private String updatedAt;
}
