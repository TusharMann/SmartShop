package in.insectiousapp.smartshop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tushar on 29-12-2016.
 */
public class ShoppingAdapter extends ArrayAdapter<Item> {


    Context context;
    ArrayList<Item> objects;

    TextView tvItemName, tvItemPrice,tvItemQuantity;
    Spinner quantity ;
    CheckBox checkBox;

    public ShoppingAdapter(Context context, int resource, ArrayList<Item> list) {
        super(context, resource,list);
        this.context=context;
        this.objects=list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            v = View.inflate(context, R.layout.all_item_adapter_layout, null);
        }

            tvItemName=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemname);
        tvItemPrice=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemprice);
        //tvItemQuantity=(Spinner) v.findViewById(R.id.tv_shoppingcartitem_itemquantitiy);
        tvItemQuantity=(TextView)v.findViewById(R.id.tv_shoppingcartitem_quantity);
        checkBox=(CheckBox)v.findViewById(R.id.cb_shoppingcartitem_billeditemcheck);

        // t1 = (TextView) v.findViewById(R.id.tv1);
        //t2 = (TextView) v.findViewById(R.id.tv2);

        //Button bnew = (Button) v.findViewById(R.id.bnew);

        // final Student s=objects.get(position);

        Item item=objects.get(position);
        tvItemName.setText(item.getItemName());
        tvItemPrice.setText(String.valueOf(item.getItemPrice()));
        tvItemQuantity.setText(String.valueOf(item.getItemQuantity()));

        if(item.getItemCheck()==1){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }


            List<String> brandlist = new ArrayList<String>();
        brandlist.add("1");
        brandlist.add("2");
        brandlist.add("3");
        brandlist.add("4");
        brandlist.add("5");
        brandlist.add("6");

//        ArrayAdapter<String> dataAdapterBrand = new ArrayAdapter<String>(context, R.layout.spinneritem1dark, brandlist);
//        dataAdapterBrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        quantity.setAdapter(dataAdapterBrand);
//
//        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
//        tvItemQuantity.setAdapter(adapter);





        return v;

    }
}

