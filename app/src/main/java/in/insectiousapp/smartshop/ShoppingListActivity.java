package in.insectiousapp.smartshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingListActivity extends AppCompatActivity {

    ListView item_listview;
    ShoppingAdapter adapter;
    Button createlist;

    ArrayList<Item> allitems,selecteditem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        setTitle("Available Items");
        item_listview=(ListView)findViewById(R.id.all_items_listview);
        createlist=(Button)findViewById(R.id.createlist);

        allitems=new ArrayList<Item>();
        selecteditem=new ArrayList<Item>();
//        Item item1=new Item(1,'tushar',4,20.0);
//        Item item2=new Item(1,'tushar',4,20.0);
//        Item item3=new Item(1,'tushar',4,20.0);
//        Item item4=new Item(1,'tushar',4,20.0);
//        Item item5=new Item(1,'tushar',4,20.0);
//        Item item6=new Item(1,'tushar',4,20.0);

        adapter=new ShoppingAdapter(this,0,allitems);

        item_listview.setAdapter(adapter);

        item_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item=allitems.get(position);
                int check=item.getItemCheck();
                if(check==0)
                {
                    check=1;
                    selecteditem.add(item);
                    Log.i("Selected",String.valueOf(selecteditem.size()));
                }
                else
                {
                    check=0;
                    selecteditem.remove(item);
                    Log.i("Selected",String.valueOf(selecteditem.size()));
                }
                item.setItemCheck(check);
                adapter.notifyDataSetChanged();

            }
        });

        Call<All_items_Json> jsonObject = ApiClient.getInterface().getAllItems();

        jsonObject.enqueue(new Callback<All_items_Json>() {
            @Override
            public void onResponse(Call<All_items_Json> call, Response<All_items_Json> response) {
                All_items_Json jsonObject1 = response.body();

                for (int i = 0; i < jsonObject1.getAllitems().size(); i++)
                    allitems.add(jsonObject1.getAllitems().get(i));

                Log.i("Size", String.valueOf(allitems.size()));

                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<All_items_Json> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();

            }
        });

        createlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer size=selecteditem.size();

                for(int i=0;i<size;i++){
                    Item item=selecteditem.get(i);
                    item.setItemCheck(0);
                    item.setItemId(i+1);
                }

                Intent intent=new Intent();
               // intent.setClass(getApplicationContext(),);
                intent.putExtra("Selected List",selecteditem);
                intent.setClass(getApplicationContext(), ShoppingCartActivity.class);
                startActivity(intent);

            }
        });



    }
}
