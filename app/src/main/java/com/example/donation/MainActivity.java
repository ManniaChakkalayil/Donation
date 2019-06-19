package com.example.donation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.Toast;
import android.content.Intent;
import java.text.NumberFormat;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {

    private int target = 10000;
    private NumberPicker n1;
    private TextView TotalView;
    private Button Donate;
    private EditText Amount;
    private RadioGroup paymentMethod;
    private ProgressBar ProgressBar;

    int donationTotal = 0;

    ArrayList<donation> DonationArrayList = new ArrayList<>();
    donation d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//       FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

      Amount = findViewById(R.id.editText);
        paymentMethod = findViewById(R.id.radioGroup);
        Donate = findViewById(R.id.button);
        Donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDonation();
            }
        });
        TotalView = findViewById(R.id.textView3);
        n1 = findViewById(R.id.numberPickerDonation);
        ProgressBar = findViewById(R.id.progressBar);

        n1.setMinValue(0);
        n1.setMaxValue(1000);
        ProgressBar.setMax(target);

        n1.setOnValueChangedListener(onValueChangeListener);
        Amount.setText("0");
    }

    private void addDonation(){

        String method = paymentMethod.getCheckedRadioButtonId() == R.id.payPal ? "PayPal" : "Direct";

        int donate = n1.getValue();
        String EnterAmount = Amount.getText().toString();
        int intAmount = Integer.parseInt(EnterAmount);

        //String donationId = "Donation " + Integer.toString(DonationArrayList.size() + 1);

        if(donate == 0){
            String text = Amount.getText().toString();
            if (!text.equals(""))
                donate = Integer.parseInt(text);
        }

        d = new donation(method, donate);
        if(donate != 0){

            DonationArrayList.add(d);

            //if donation not zero
            donationTotal += donate;

            ProgressBar.setProgress(donationTotal);
            String donationTotalString = "$" + donationTotal;
            TotalView.setText(donationTotalString);
            n1.setValue(0);
        }
        else if (intAmount > 0){

            d = new donation(method,intAmount);
            DonationArrayList.add(d);

            //if donation not zero
            donationTotal += intAmount;

            ProgressBar.setProgress(donationTotal);
            String donationTotalString = "$" + donationTotal;
            TotalView.setText(donationTotalString);
        }
        else{
            Toast.makeText(this,"Please donate something",Toast.LENGTH_SHORT).show();
        }
    }

    NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
            Toast.makeText(MainActivity.this,"selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
        }
    };

    NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
        @Override
        public String format(int value) {
            return NumberFormat.getCurrencyInstance(Locale.CANADA).format((long )value).toString();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(!DonationArrayList.isEmpty()) {

            Bundle b = new Bundle();
            b.putParcelableArrayList("myKey", DonationArrayList);

            Intent intent = new Intent(MainActivity.this, donationreport.class);

            intent.putExtras(b);

            startActivity(intent);

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"Please Donate first", Toast.LENGTH_SHORT);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }
}

