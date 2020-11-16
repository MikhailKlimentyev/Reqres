package models;


import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class UserListResponse extends AbstractListResponse {

    @Expose
    private List<User> data;
}
