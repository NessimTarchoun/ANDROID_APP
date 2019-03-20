package com.example.app;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.app.MainActivity.db;
import static com.example.app.MainActivity.getAppFirstInstallTime;
import static com.example.app.MainActivity.getDate;


public class totale extends AppCompatActivity {
    Long date;
    TextView t, dateview;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totale);

        t =findViewById(R.id.montanttotle);
        t.setText(Float.toString(db.somme()));
        date= getAppFirstInstallTime(this);
        String d = getDate(date, "dd/MM/yyyy");
        dateview=findViewById(R.id.datep);
        dateview.setText(d);


    }
    public void onResume(){
        super.onResume();
        t.setText(Float.toString(db.somme()));
    }

}
