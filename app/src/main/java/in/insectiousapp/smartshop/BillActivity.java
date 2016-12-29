package in.insectiousapp.smartshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Item> billedItemList;
    BillAdapter adapter;

    ListView lvShoppingCart;
    Button bGenerateBill;
    float totalAmount=0f;
    float price;
    float qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        bGenerateBill=(Button)findViewById(R.id.btn_generateBill);
        bGenerateBill.setOnClickListener(this);

        Intent ii=getIntent();
        billedItemList=(ArrayList<Item>)ii.getSerializableExtra("billed_item_list");

        for(int i=0; i<billedItemList.size(); i++)
        {
            Log.i("billedlist", billedItemList.get(i).getItemName());
            price=billedItemList.get(i).getItemPrice();
            qty=billedItemList.get(i).getItemQuantity();
            totalAmount=totalAmount+(price*qty);
        }

        lvShoppingCart = (ListView) findViewById(R.id.list_vieww);

        LayoutInflater l = getLayoutInflater();
        adapter = new BillAdapter(this, 0, billedItemList, l);
        lvShoppingCart.setAdapter(adapter);
        bGenerateBill.setText("Checkout :"+String.valueOf(totalAmount));

    }

    @Override
    public void onClick(View v) {



    }
}
