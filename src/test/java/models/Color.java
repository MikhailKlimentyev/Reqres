package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Color {

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private int year;
    @Expose
    private String color;
    @Expose
    @SerializedName("pantone_value")
    private String pantoneValue;
}
