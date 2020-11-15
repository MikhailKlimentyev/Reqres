package models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class UserListResponse {

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
    private List<User> data;
    @Expose
    private Support support;
}
