package ogul.ucarsu.com.qrcode;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ogul.ucarsu.com.qrcode.DB.Veritabani;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private  static final int DialogHakkinda=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);


        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);	//Barcode Scanner to scan for us

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {

            TextView tvlink=(TextView) findViewById(R.id.linktext);
            if (resultCode == RESULT_OK) {
                tvlink.setText(intent.getStringExtra("SCAN_RESULT"));
                Veritabani db=new Veritabani(getApplicationContext());
                db.kayitEkle(tvlink.getText().toString());
                tekrarTara();
            } else if (resultCode == RESULT_CANCELED) {
                tvlink.setText("QR Kod Okuma İptal Edildi..");
                tekrarTara();
            }
        }
    }

    private void tekrarTara() {

        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Sonuç");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(toolbar);

         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

                 Intent i = new Intent(MainActivity.this, MenuActivity.class);
                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(i);
                 MainActivity.this.finish();
              }
          });

         ImageButton tara = (ImageButton) findViewById(R.id.tarabutton);

        tara.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent = new Intent("com.google.zxing.client.android.SCAN");
              intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
              startActivityForResult(intent, 0);	//Barcode Scanner to scan for us
          }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {

            case R.id.hakkinda:
                Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=ogulcanucarsu");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;

            case R.id.cikis:
                cikis();
                return true;

            case R.id.hakkindaDialog:
                showDialog(DialogHakkinda);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        Dialog dialog=null;

        switch (id)
        {
            case DialogHakkinda:
                dialog=getHakkinda();
                break;

            default:
                dialog=null;
        }
        return  dialog;
    }

    public Dialog getHakkinda()
    {

        LayoutInflater inflater =LayoutInflater.from(this);
        View layout=inflater.inflate(R.layout.activity_hakkimizda,null);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Geliştirici Hakkında");
        builder.setView(layout);
        final AlertDialog dialog=builder.create();


        return dialog;
    }

    public void cikis()
    {
        finish();
        System.exit(0);
    }


}
