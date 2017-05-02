package ogul.ucarsu.com.qrcode;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ogul.ucarsu.com.qrcode.DB.Veritabani;

/**
 * Created by ogulc on 19.02.2017.
 */

public class KaydedilenDataActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TableLayout tablo;
    private  static final int DialogHakkinda=1;
    TextView tw;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listele);
        try
        {
            tablo=(TableLayout)findViewById(R.id.tablo);
            tw=(TextView)findViewById(R.id.twDBbos);

            mToolbar = (Toolbar) findViewById(R.id.toolbar_search);
            mToolbar.setTitle("Okutulan QR Codeların İçerikleri");
            mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
            mToolbar.setNavigationIcon(R.drawable.ic_back_white);
            setSupportActionBar(mToolbar);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(KaydedilenDataActivity.this, MenuActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    KaydedilenDataActivity.this.finish();
                }
            });

            Veritabani db=new Veritabani(getApplicationContext());
            ArrayList<String> gelenData=new ArrayList<>();
            gelenData=db.kayitlariGetir();

            if(gelenData.size()!=0)
            {
                tw.setText("Okuttuğunuz QR Code'lar..");
                for(int i =0; i<gelenData.size();i++)
                {
                    int k;
                    k=i;
                    k++;
                    TableRow row = new TableRow(getApplicationContext());
                    row.setGravity(Gravity.CENTER);
                    row.setOrientation(TableRow.HORIZONTAL);

                    TextView tw_data = new TextView(getApplicationContext());
                    tw_data.setLayoutParams(new  TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    tw_data.setTextColor(Color.BLACK);
                    tw_data.setPadding(5,4,2,0);
                    tw_data.setTypeface(null, Typeface.BOLD);
                    tw_data.setText(k+"-) "+ gelenData.get(i).toString());
                    Linkify.addLinks(tw_data,Linkify.WEB_URLS);
                    tw_data.setLinksClickable(true);

                    row.addView(tw_data);
                    tablo.addView(row);
                }
            }
            else
            {
                tw.setText("Daha Önceden Hiç Qr Code okutmamışsınız!");
            }
        }
        catch (Exception e )
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(KaydedilenDataActivity.this);
            builder1.setMessage("Beklenmeyen bir hata oluştu..Uygulamayı Tekrardan Başlatalım mı ?");
            builder1.setCancelable(false);
            builder1.setPositiveButton(
                    "Tamam",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            KaydedilenDataActivity.this.finish();
                            Intent mIntent = new Intent(KaydedilenDataActivity.this, MenuActivity.class);
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
