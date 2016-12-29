package in.insectiousapp.smartshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Codev on 12/29/2016.
 */
public class ShoppingCartAdapter extends ArrayAdapter<Item> {


    Context context;
    LayoutInflater l;
    List<Item> objects;

    TextView tvItemName, tvItemPrice, tvItemQuantity;
    CheckBox cbItemSelected;
    int check=0;

//    public ShoppingCartAdapter(Context context, int resource, List<ClipData.Item> objects) {
//        super(context, resource, objects);
//    }


    public ShoppingCartAdapter(Context context, int resource, List<Item> objects, LayoutInflater l) {
        super(context, resource, objects);

        this.context=context;
        this.objects=objects;
        this.l=l;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            v = l.inflate(R.layout.item_shoppingcartlayout, null);
        }

        tvItemName=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemname);
        tvItemPrice=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemprice);
        tvItemQuantity=(TextView)v.findViewById(R.id.tv_shoppingcartitem_itemquantitiy);
        cbItemSelected=(CheckBox)v.findViewById(R.id.cb_shoppingcartitem_billeditemcheck);


       // t1 = (TextView) v.findViewById(R.id.tv1);
        //t2 = (TextView) v.findViewById(R.id.tv2);

        //Button bnew = (Button) v.findViewById(R.id.bnew);

       // final Student s=objects.get(position);

        Item item=objects.get(position);
        tvItemName.setText(item.getItemName());
        tvItemPrice.setText(String.valueOf(item.getItemPrice()));
        tvItemQuantity.setText(String.valueOf(item.getItemQuantity()));

        check=item.getItemCheck();
        if(check==1)
        {
            cbItemSelected.setChecked(true);
        }
        else
        {
            cbItemSelected.setChecked(false);
        }

        return v;

    }
}
