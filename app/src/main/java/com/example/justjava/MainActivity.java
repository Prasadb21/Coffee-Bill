package com.example.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    int numerOfCoffees = 0;
//    public void submitOrder(View view) {
//
//        display(numerOfCoffees);
//        displayPrice(numerOfCoffees*5);
//    }
int numerOfCoffees = 0;
    public void submitOrder(View view) {
        CheckBox whippedCheckbox = (CheckBox) findViewById(R.id.whipped_checkbox);
        boolean hasWC = whippedCheckbox.isChecked();
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChoco = chocolateCheckbox.isChecked();
        EditText nameInput = (EditText) findViewById(R.id.Name_view);
        String name = nameInput.getText().toString();
        int calculation = calculatePrice(hasWC , hasChoco);
        String priceMessage = createOrderSummary(calculation ,hasWC , hasChoco , name);


        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL , "Prasad");
        email.putExtra(Intent.EXTRA_SUBJECT , "Your bill");
        email.putExtra(Intent.EXTRA_TEXT , priceMessage);
        email.setType("priceMessage/rfc822");
        if(email.resolveActivity(getPackageManager()) != null)
        {
            startActivity(Intent.createChooser(email , "Choose an Email client:"));
        }

    }

    private int calculatePrice(Boolean wc , Boolean cc){
        if(wc)
        {
            return numerOfCoffees*5 + 2*numerOfCoffees;
        }
        else if(cc){
            return numerOfCoffees * 5 + 3*numerOfCoffees;
        }
        else
        {
            return numerOfCoffees*5;
        }
    }

    private String createOrderSummary(int price , boolean haswc , boolean choco , String name1)
    {
        String printMessage ="Name: " + name1;
        printMessage = printMessage + "\n Add Whipped cream? " + haswc;
        printMessage = printMessage + "\n Add Chocolate? " + choco;
        printMessage = printMessage + "\n Quantity: " + numerOfCoffees;
        printMessage = printMessage + "\n Total: " + price;
        printMessage = printMessage + "\n Thank you!";
        return printMessage;
    }
    public void increment(View view) {
        if(numerOfCoffees>=100 )
        {
            Toast.makeText(this , "You cannot have more then 100 coffee" , Toast.LENGTH_SHORT).show();
            return;
        }
        numerOfCoffees = numerOfCoffees + 1;
        display(numerOfCoffees);
    }

    public void decrement(View view) {
        if(numerOfCoffees==1)
        {
            Toast.makeText(this , "You cannot have less then 1 coffee", Toast.LENGTH_SHORT).show();
            return;

        }
        numerOfCoffees = numerOfCoffees - 1;
        display(numerOfCoffees);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}