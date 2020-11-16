package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public abstract class AbstractListResponse {

    @Expose
    private int page;
    @SerializedName("per_page")
    private int perPage;
    @Expose
    private int total;
    @Expose
    @SerializedName("total_pages")
    private int totalPages;
    @Expose
    private Support support;
}
