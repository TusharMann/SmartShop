package in.insectiousapp.smartshop;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final String TAG = ShoppingCartActivity.class.getSimpleName();
    private ArrayAdapter<String> arrayAdapter;
    private LinearLayout mContainer;
    private ProgressDialog progressDialog;

    private static String[] mPermissions = { Manifest.permission.ACCESS_FINE_LOCATION};
    private MyApp.OnListRefreshListener onListRefreshListener;

    public HashMap<String, Integer> regionMap;

    //added code

    ArrayList<Item> data;
    ShoppingCartAdapter adapter;

    ListView lvShoppingCart;
    Button bGenerateBill;

    //--added code

    @Override
    protected void onResume() {
        super.onResume();
        onListRefreshListener = new MyApp.OnListRefreshListener() {
            @Override
            public void onListRefresh() {
                notifyListChange();
            }
        };
        MyApp.getInstance().onListRefreshListener = onListRefreshListener;
        MyApp.getInstance().context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        //setHasOptionsMenu(true);

        regionMap=new HashMap<>();
        regionMap.put("iOS Room", 3);
        regionMap.put("Ruby Room", 6);
        regionMap.put("Python Room", 9);
        regionMap.put("Git Room", 12);
        regionMap.put("Test Room", 15);
        regionMap.put("Office", 18);
        regionMap.put("Git Room", 21);

        if (!havePermissions()) {
            Log.i(TAG, "Requesting permissions needed for this app.");
            requestPermissions();
        }

        if(!isBlueEnable()){
            Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(bluetoothIntent);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        mContainer = (LinearLayout) findViewById(R.id.activity_main);



        List<String> items = new ArrayList<>(MyApp.getInstance().regionNameList);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!MyApp.getInstance().regionList.isEmpty()) {
                    try {
//                        String beaconSSN = MyApp.getInstance().regionList.get(i).getId2().toHexString();
//                        Intent regionIntent = new Intent(ShoppingCartActivity.this,RegionDetailActivity.class);
//                        regionIntent.putExtra("beacon_ssn",beaconSSN);
//                        regionIntent.putExtra("name", MyApp.getInstance().regionNameList.get(i));
//                        startActivity(regionIntent);
                    } catch (ArrayIndexOutOfBoundsException e) {/*Do nothing*/}
                }
            }
        });
        listView.setAdapter(arrayAdapter);

        //started

        bGenerateBill=(Button)findViewById(R.id.btn_generateBill);
        bGenerateBill.setOnClickListener(this);
        lvShoppingCart = (ListView) findViewById(R.id.list_view_shoppingcart);
        data = new ArrayList<Item>();

        Item item1=new Item(1, "Nirma", 78, 45.4f, 0);
        Item item2=new Item(2, "Nirma2", 68, 57.4f, 1);
        Item item3=new Item(3, "Nirma3", 68, 57.4f, 0);
        Item item4=new Item(4, "Nirma4", 68, 57.4f, 1);
        Item item5=new Item(5, "Nirma5", 68, 57.4f, 0);
        Item item6=new Item(6, "Nirma6", 68, 57.4f, 1);
        Item item7=new Item(7, "Nirma7", 68, 57.4f, 0);
        Item item8=new Item(8, "Nirma8", 68, 57.4f, 0);

        data.add(item1);
        data.add(item2);
        data.add(item3);
        data.add(item4);
        data.add(item5);
        data.add(item6);
        data.add(item7);
        data.add(item8);

        LayoutInflater l = getLayoutInflater();
        adapter = new ShoppingCartAdapter(this, 0, data, l);
        lvShoppingCart.setAdapter(adapter);
//        lvShoppingCart.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
//            {
//                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
//            }
//        });

        lvShoppingCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("slct", "item clicked");
                Item item=data.get(position);
                int check=item.getItemCheck();
                if(check==0)
                {
                    check=1;
                }
                else
                {
                    check=0;
                }
                item.setItemCheck(check);
                adapter.notifyDataSetChanged();
            }
        });



    }

    private boolean isBlueEnable() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        return bluetoothAdapter.isEnabled();

    }

    private boolean havePermissions() {
        for(String permission:mPermissions){
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return  false;
            }
        }
        return true;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                mPermissions, PERMISSIONS_REQUEST_CODE);
    }


    private void showLinkToSettingsSnackbar() {
        if (mContainer == null) {
            return;
        }
        Snackbar.make(mContainer,
                R.string.permission_denied_explanation,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.settings, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Build intent that displays the App settings screen.
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",
                                BuildConfig.APPLICATION_ID, null);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).show();
    }


    private void showRequestPermissionsSnackbar() {
        if (mContainer == null) {
            return;
        }
        Snackbar.make(mContainer, R.string.permission_rationale,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Request permission.
                        ActivityCompat.requestPermissions(ShoppingCartActivity.this,
                                mPermissions,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return;
        }
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)) {
                    Log.i(TAG, "Permission denied without 'NEVER ASK AGAIN': " + permission);
                    showRequestPermissionsSnackbar();
                } else {
                    Log.i(TAG, "Permission denied with 'NEVER ASK AGAIN': " + permission);
                    showLinkToSettingsSnackbar();
                }
            } else {
                Log.i(TAG, "Permission granted, building GoogleApiClient");
            }
        }
    }

    private void notifyListChange(){
        if(arrayAdapter != null){
            List<String> items = new ArrayList<>(MyApp.getInstance().regionNameList);
            arrayAdapter.clear();
            arrayAdapter.addAll(items);
            arrayAdapter.notifyDataSetChanged();
            listOfCurrentRegion(items);
        }
    }

    private void listOfCurrentRegion(List<String> items)
    {

        for(int i=0; i<data.size(); i++)
        {
              Item item=data.get(i);
                int check=item.getItemCheck();
            if(check==2)
            {
                item.setItemCheck(0);
            }
        }
        adapter.notifyDataSetChanged();

        for(int i=0; i<items.size(); i++)
        {
            //Log.i("regionnn", "Region is :"+items.get(i));


        //now we will search in the list

            int highRange=regionMap.get(items.get(i));
            int lowRange=regionMap.get(items.get(i))-2;

            //Log.i("regionnn", "Range of region of "+items.get(i)+" is :"+String.valueOf(highRange)+"-"+String.valueOf(lowRange));

            for(int j=0; j<data.size(); j++)
            {
                Item item=data.get(j);
                int check=item.getItemCheck();
                int id=item.getItemId();

                if ( check==1 )
                {

                }
                else
                {
                    if ((id <= highRange) && (id >= lowRange))
                    {
                       // Log.i("regionnn", "value changed for :" + item.getItemName() + "]" + check + " range is :" + highRange + "-" + lowRange + " id:" + id);
                        item.setItemCheck(2);
                    }
                }


            }
            adapter.notifyDataSetChanged();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_logout){
            //logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApp.getInstance().onListRefreshListener = null;
        MyApp.getInstance().context = null;
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
        ArrayList<Item> pickedItem=new ArrayList<Item>();
        for(int i=0; i<data.size(); i++)
        {
            Item item= data.get(i);
            Log.i("slct", "item :"+i+":"+String.valueOf(item.getItemCheck()));
            if(item.getItemCheck()==1)
            {
                pickedItem.add(item);
            }
        }


        for(int j=0; j<pickedItem.size(); j++)
        {
            Item item=pickedItem.get(j);
            item.setItemCheck(0);
        }

        Intent i=new Intent();
        i.setClass(ShoppingCartActivity.this, BillActivity.class);
        i.putExtra("billed_item_list", pickedItem);
        startActivity(i);

    }

}
