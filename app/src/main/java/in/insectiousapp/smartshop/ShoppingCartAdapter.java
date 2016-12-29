package in.insectiousapp.smartshop;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Codev on 12/29/2016.
 */
public class ShoppingCartAdapter extends ArrayAdapter<ClipData.Item> {


    Context context;
    LayoutInflater l;
    List<ClipData.Item> objects;

    public ShoppingCartAdapter(Context context, int resource, List<ClipData.Item> objects, LayoutInflater l) {
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

        final TextView t1, t2;


       // t1 = (TextView) v.findViewById(R.id.tv1);
        //t2 = (TextView) v.findViewById(R.id.tv2);

        //Button bnew = (Button) v.findViewById(R.id.bnew);

       // final Student s=objects.get(position);


        return v;

    }
}
