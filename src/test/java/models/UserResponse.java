package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UserResponse {

    @Expose
    private User data;
    @Expose
    private Support support;
}
