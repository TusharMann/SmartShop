package in.insectiousapp.smartshop;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Tushar on 29-12-2016.
 */
public class All_items_Json {
    @SerializedName("error")
    Boolean error;

    public Boolean  getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    @SerializedName("allitems")
    ArrayList<Item> allitems;

    public ArrayList<Item> getAllitems() {
        return allitems;
    }

    public void setMovieList(ArrayList<Item> allitems) {
        this.allitems = allitems;
    }
}

