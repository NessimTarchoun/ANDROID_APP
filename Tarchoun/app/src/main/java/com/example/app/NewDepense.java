package com.example.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewDepense {
    int num;
    public static float total;
    private String achat;
    private String prix;
    String date;

    public NewDepense(String achat, String prix) {
        this.achat = achat;
        this.prix = prix;
        String pattern = "MM/dd/yyyy HH:mm:ss";

        DateFormat d0 = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();

        date = d0.format(today);

    }

    public float getTotal() {return total; }

    public void setTotal(float total) {this.total = total;}

    public String getAchat() {return achat;}

    public void setAchat(String achat) {this.achat = achat;}

    public String getPrix() {return prix;}

    public void setPrix(String prix) {this.prix = prix;}

    public String getDate() {return date;}

    public void setNum(int num) {this.num = num;}

    public void setDate(String date) {this.date = date;}

    public int getNum() {return num;}
}
