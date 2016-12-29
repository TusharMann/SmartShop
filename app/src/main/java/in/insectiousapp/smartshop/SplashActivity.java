package in.insectiousapp.smartshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashActivity extends Activity {

    TextView tvTitle, tvSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        tvTitle=(TextView)findViewById(R.id.tvSplashTitle);
        tvSubTitle=(TextView)findViewById(R.id.tvSplashSubTitle);



        //tvSubTitle.setTypeface(EasyFonts.caviarDreamsBold(this));

       // checkForFirstTime();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(getApplicationContext(),ShoppingListActivity.class);
                startActivity(i);
            }
        }, 2500);

    }


}
