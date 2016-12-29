package in.insectiousapp.smartshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Codev on 12/29/2016.
 */
public class BillAdapter extends ArrayAdapter<Item> {


    Context context;
    LayoutInflater l;
    List<Item> objects;

    TextView tvItemName, tvItemPrice, tvItemQuantity, tvCost;
    int check=0;
    LinearLayout lvBack;

//    public ShoppingCartAdapter(Context context, int resource, List<ClipData.Item> objects) {
//        super(context, resource, objects);
//    }


    public BillAdapter(Context context, int resource, List<Item> objects, LayoutInflater l) {
        super(context, resource, objects);

        this.context=context;
        this.objects=objects;
        this.l=l;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            v = l.inflate(R.layout.item_shoppingcartlayout2, null);
        }

        tvItemName=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemname);
        tvItemPrice=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemprice);
        tvItemQuantity=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemquantitiy);
        tvCost=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemcost);
        lvBack=(LinearLayout)v.findViewById(R.id.lv_back);

       // t1 = (TextView) v.findViewById(R.id.tv1);
        //t2 = (TextView) v.findViewById(R.id.tv2);

        //Button bnew = (Button) v.findViewById(R.id.bnew);

       // final Student s=objects.get(position);

        Item item=objects.get(position);
        tvItemName.setText(item.getItemName());
        tvItemPrice.setText(String.valueOf(item.getItemPrice()));
        tvItemQuantity.setText(String.valueOf(item.getItemQuantity()));

        float price=item.getItemPrice();
        int quantity=item.getItemQuantity();

        float amount=price*quantity;
        tvCost.setText(String.valueOf(amount));

        return v;

    }
}
