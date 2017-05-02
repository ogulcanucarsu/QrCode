package ogul.ucarsu.com.qrcode.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ogulc on 2.05.2017.
 */

public class Veritabani extends SQLiteOpenHelper {

    private static final String VERITABANI_ISMI="QrCodeOkunanlar";
    private static final int VERITABANI_VERSIYON=1;
    private static final String TABLO_ISMI="okunan_qrcode_tablosu";


    private  static final String ID="_id";
    private  static final String TEXT="text";

    public Veritabani(Context context)
    {
        super(context,VERITABANI_ISMI,null,VERITABANI_VERSIYON);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table="CREATE TABLE " + TABLO_ISMI +
                "(" +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TEXT +" TEXT);";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLO_ISMI);
        onCreate(db);
    }

    public long kayitEkle(String text)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TEXT,text);


        long id=db.insert(TABLO_ISMI,null,cv);

        db.close();
        return id;

    }

    public ArrayList kayitlariGetir()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        String[] sutunlar= new String[]{TEXT,ID};
        Cursor cs= db.query(TABLO_ISMI,sutunlar,null,null,null,null,ID+" desc");
        int textt=cs.getColumnIndex(TEXT);
        int id=cs.getColumnIndex(ID);

        ArrayList<String> donenCevap = new ArrayList<>();

        for(cs.moveToFirst(); !cs.isAfterLast(); cs.moveToNext())
        {
           donenCevap.add(cs.getString(textt));
        }
        db.close();
        int a=donenCevap.size();

        return donenCevap;
    }


}
