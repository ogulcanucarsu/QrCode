package ogul.ucarsu.com.qrcode;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by ogulc on 19.02.2017.
 */

public class MenuActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private  static final int DialogHakkinda=1;
    Button qrCode,kaydedilenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        try
        {
            toolbar = (Toolbar) findViewById(R.id.toolbar_search);
            toolbar.setTitle("Ana Menü");
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolbar);

            qrCode=(Button)findViewById(R.id.qrCode);
            kaydedilenData=(Button)findViewById(R.id.hakkimizda);


            qrCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MenuActivity.this,MainActivity.class));
                    MenuActivity.this.finish();
                }
            });

            kaydedilenData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MenuActivity.this,KaydedilenDataActivity.class));
                    MenuActivity.this.finish();
                }
            });
        }
        catch (Exception e)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MenuActivity.this);
            builder1.setMessage("Beklenmeyen bir hata oluştu..Uygulamayı Tekrardan Başlatalım mı ?");
            builder1.setCancelable(false);
            builder1.setPositiveButton(
                    "Tamam",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            MenuActivity.this.finish();
                            Intent mIntent = new Intent(MenuActivity.this, MenuActivity.class);
                            startActivity(mIntent);
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
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
