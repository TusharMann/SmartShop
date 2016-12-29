package in.insectiousapp.smartshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {

    ArrayList<Item> billedItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Intent ii=getIntent();
        billedItemList=(ArrayList<Item>)ii.getSerializableExtra("billed_item_list");

        Log.i("slct", "reached bill activity");

        for(int i=0; i<billedItemList.size(); i++)
        {

        }

    }
}
