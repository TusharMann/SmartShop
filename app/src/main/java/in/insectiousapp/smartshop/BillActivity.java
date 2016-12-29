package in.insectiousapp.smartshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Item> billedItemList;
    BillAdapter adapter;

    ListView lvShoppingCart;
    Button bGenerateBill;
    float totalAmount=0f;
    float price;
    float qty;

    ProgressDialog progressDialog;
    EditText etName, etMob;
    String overAllString=null;
    String s1=null;
    String s2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);


        setTitle("Bill & Checkout");
        etName=(EditText)findViewById(R.id.et1);
        etMob=(EditText)findViewById(R.id.et2);

        bGenerateBill=(Button)findViewById(R.id.btn_generateBill);
        bGenerateBill.setOnClickListener(this);

        Intent ii=getIntent();
        billedItemList=(ArrayList<Item>)ii.getSerializableExtra("billed_item_list");

        progressDialog=new ProgressDialog(this);
        //progressDialog.setCancelable(false);

        for(int i=0; i<billedItemList.size(); i++)
        {
            Log.i("billedlist", billedItemList.get(i).getItemName()+"::"+billedItemList.get(i).getItemId());
            price=billedItemList.get(i).getItemPrice();
            qty=billedItemList.get(i).getItemQuantity();
            totalAmount=totalAmount+(price*qty);
            s1=String.valueOf(billedItemList.get(i).getItemId());
            s2=String.valueOf(billedItemList.get(i).getItemQuantity());
            overAllString=overAllString+"-"+s1+"|"+s2;

        }
        if(overAllString!=null) {
            overAllString = overAllString.substring(5);
        }
        lvShoppingCart = (ListView) findViewById(R.id.list_vieww);

        LayoutInflater l = getLayoutInflater();
        adapter = new BillAdapter(this, 0, billedItemList, l);
        lvShoppingCart.setAdapter(adapter);
        bGenerateBill.setText("Checkout :"+String.valueOf(totalAmount));

    }

    @Override
    public void onClick(View v) {

    requestPostDataOnServer();

    }

    public void requestPostDataOnServer() {
        progressDialog.setTitle("Saving timetable");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        String serverPostDataUrl="http://10.0.0.143/beacathon/v1/calculatebill";
                //"http://api.thingspeak.com/update?key="+writeKey+"&field6="+fixedTimeTable+"&field7="+CRDetails;


        MyVolley.init(this);
        RequestQueue queue = MyVolley.getRequestQueue();

        StringRequest myReq = new StringRequest(Request.Method.POST, serverPostDataUrl
                , reqCreateNewAccountSuccessListener(), reqCreateNewAccountErrorListener()) {

            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();

                headers.put("Content-Type", "application/x-www-form-urlencoded");

                return headers;
            }

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

//                params.put("api_key", authorizationKey);
//                params.put("name", spYear.getSelectedItem().toString()+"-"+spBranch.getSelectedItem().toString()+"-"+spBatch.getSelectedItem().toString());
                params.put("name", etName.getText().toString());
                params.put("mobile", etMob.getText().toString());
                params.put("list", overAllString);
               // params.put("field4", "Field4");
//                params.put("field5", "Field5");
//                params.put("field6", "Field6");
//                params.put("field7", "Field7");
//                params.put("field8", "Field8");
//                params.put("public_flag", "true");

                return params;
            }
        };

        myReq.setRetryPolicy(new DefaultRetryPolicy(2000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(myReq);

    }

    private com.android.volley.Response.Listener<String> reqCreateNewAccountSuccessListener() {
        return new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String serverResponse) {


                Log.i("responsecheckingg", "Server response : " + serverResponse.toString());
               Toast.makeText(getApplicationContext(), "Check out done !", Toast.LENGTH_LONG).show();
                progressDialog.hide();

            }
        };
    }

    private com.android.volley.Response.ErrorListener reqCreateNewAccountErrorListener() {
        return new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("responsecheckingg", "Server Error response : " + error.toString());
                Toast.makeText(getApplicationContext(), "Server Error !", Toast.LENGTH_LONG).show();
                 progressDialog.hide();
            }
        };
    }

}
