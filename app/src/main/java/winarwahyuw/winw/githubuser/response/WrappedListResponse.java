package winarwahyuw.winw.githubuser.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class WrappedListResponse<T> {
    @SerializedName("total_count")
    private String total_count;
    @SerializedName("incomplete_results")
    private String incomplete_results;
    @SerializedName("items")
    private List<T> items = new ArrayList<>();

    public WrappedListResponse(String total_count, String incomplete_results, List<T> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(String incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

