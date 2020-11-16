package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class ColorListResponse extends AbstractListResponse {

    @Expose
    private List<Color> data;
}
