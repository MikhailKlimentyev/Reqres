package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ColorResponse {

    @Expose
    private Color data;
    @Expose
    private Support support;
}
