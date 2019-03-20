package com.example.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView mylist;
    public  static DBAdapter db;
    public DepenseAdapter array;
    public ArrayList<NewDepense> database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist = findViewById(R.id.liste);
        db = new DBAdapter(this);

        database = new ArrayList<NewDepense>();
        database = db.afficher();

         array = new DepenseAdapter(this, R.layout.activity_new_depense, database);


        mylist.setAdapter(array);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("supprimer?")
                        .setMessage("êtes vous sûr?")
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        db.delete(database.get(position).getNum());
                                        database = db.afficher();
                                        array = new DepenseAdapter(MainActivity.this, R.layout.activity_new_depense, database);
                                        mylist.setAdapter(array);
                                        Toast.makeText(MainActivity.this, "supprimé", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu ajoutmenu) {
        super.onCreateOptionsMenu(ajoutmenu);

        getMenuInflater().inflate(R.menu.menu, ajoutmenu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.ajoutermenu) {
            Intent intent = new Intent(MainActivity.this, addactivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.totale) {
            Intent intent = new Intent(MainActivity.this, totale.class);
            startActivity(intent);
        }
        return true;
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());

    }

    public static long getAppFirstInstallTime(Context context) {
        PackageInfo packageInfo;
        try {
            if (Build.VERSION.SDK_INT > 8) {
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                return packageInfo.firstInstallTime;
            } else {
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
                String sAppFile = appInfo.sourceDir;
                return new File(sAppFile).lastModified();
            }
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }
}
