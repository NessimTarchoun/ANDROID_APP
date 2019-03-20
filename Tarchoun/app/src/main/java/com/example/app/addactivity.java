package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class addactivity extends AppCompatActivity {
    EditText achat, prix;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);

        achat = findViewById(R.id.editachat);
        prix = findViewById(R.id.editprix);
        b = findViewById(R.id.add);

//        final DBAdapter db = new DBAdapter(this);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kadhya= achat.getText().toString();
                String soum = prix.getText().toString();
                 DBAdapter db = new DBAdapter(addactivity.this);
                NewDepense depense = new NewDepense(kadhya , soum);
                db.ajoutdepense(depense);
                Intent intent = new Intent (addactivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}



















