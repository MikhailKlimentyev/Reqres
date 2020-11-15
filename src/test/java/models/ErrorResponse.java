package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ErrorResponse {

    @Expose
    private String error;
}
