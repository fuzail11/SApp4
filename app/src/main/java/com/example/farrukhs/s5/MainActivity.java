package com.example.farrukhs.s5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {

        if (quantity == 20) {
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    public void decrement(View view) {

        if (quantity == 1) {

            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);

    }

    public void submitorder(View view) {

        EditText name = (EditText) findViewById(R.id.name_id);
        String cusname = name.getText().toString();

        CheckBox icecream = (CheckBox) findViewById(R.id.cream);
        boolean checkcre = icecream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean checkcho = chocolate.isChecked();
        CheckBox juice = (CheckBox) findViewById(R.id.juice);
        boolean checkjus = juice.isChecked();


        int calP = calcu(checkcre, checkcho, checkjus);
        String summary = sammaryoder(cusname, calP, checkcre, checkcho, checkjus);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for" + cusname);
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);


            displaySummary(summary);
        }
    }
    private int calcu(boolean iceP, boolean choP, boolean jusP) {
        int cost = 5;

        if (iceP) {
            cost = cost + 1;
        }
        if (choP) {
        }
        cost = cost + 4;

        if (jusP) {
            cost = cost + 6;
        }

        return quantity * cost;

    }

    public String sammaryoder(String name, int price, boolean showicecrean, boolean showchoco, boolean showjus) {
        String pricemsg = "Thank you\n";
        pricemsg += "\n" + name;
        pricemsg += "\nQuantity=" + quantity;
        pricemsg += "\nPrize=" + price;
        pricemsg += "\nIcecream=" + showicecrean;
        pricemsg += "\nChocolate" + showchoco;
        pricemsg += "\nJuice" + showjus;
        return pricemsg;
    }

    public void displayQuantity(int quan) {
        TextView q = (TextView) findViewById(R.id.quanity_id);
        q.setText("" + quan);

    }

    public void displaySummary(String summ) {
        TextView summar = (TextView) findViewById(R.id.summary_id);
        summar.setText("" + summ);
    }


}
