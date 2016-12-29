package in.insectiousapp.smartshop;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tushar on 29-12-2016.
 */
public interface ApiClientInterface {

    @GET("v1/allitems")
    Call<All_items_Json> getAllItems();

}
