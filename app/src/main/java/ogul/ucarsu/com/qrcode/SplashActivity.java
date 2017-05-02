package ogul.ucarsu.com.qrcode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ogulc on 19.02.2017.
 */

public class SplashActivity extends Activity{

    private Context mCtx=SplashActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                    Intent i;

                    i = new Intent(mCtx, MenuActivity.class);

                    startActivity(i);
                    SplashActivity.this.finish();

                } catch (Exception e) {
                    Log.e("Splash Error",e.toString());
                }
            }
        };

        background.start();



    }


}
