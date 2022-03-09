package com.example.cofeeorderingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SELECT_CONTACT = 1;
    int quantity=0;
    String na;

    String   priceMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int pofcoffes=5;

public void INCREMENT (View view)
{

    quantity = quantity +1;


    displayQuantity(quantity);
}
public void DECREMENT(View view)
{
if(quantity>0)
    quantity=quantity-1;
    displayQuantity(quantity);

}
public String mess(String priceMessage)
{
    TextView priceTextView =(TextView) findViewById(R.id.price_text_view);
    priceTextView.setTextSize(15);
    priceTextView.setText(priceMessage);
    return  priceMessage;
}



    public void submitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.Name);
        String na = name.getText().toString();
        CheckBox cbChoco = (CheckBox) findViewById(R.id.Chocolate);
        Boolean IsChocolate = cbChoco.isChecked();
        CheckBox cbWhippedCream = (CheckBox) findViewById(R.id.WhippedCream);
        Boolean IsWhippedCream = cbWhippedCream.isChecked();

        int price = calculateprice(IsWhippedCream, IsChocolate);

        mess(createordersummary(IsWhippedCream, IsChocolate, na, price));

    }
    public void Email(View view)
    {
    Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_SUBJECT,""+na);

        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        intent.setData(Uri.parse("mailto:"));
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }

    }

    public  void OnlineDelivery(View view)
{
    Intent intent=new Intent(Intent.ACTION_VIEW);

    intent.setData(Uri.parse("geo:0,900"));
    if(intent.resolveActivity(getPackageManager())!=null)
    {
        startActivity(intent);
    }

}


    private void displayQuantity(int quantity)
    {
        TextView quantityTextview= (TextView) findViewById(R.id.quantity_text_view);
        quantityTextview.setText(""+quantity);
        if(quantity==0)
        {
            Toast.makeText(this,"You cannot have less than 1 cup of cofee",Toast.LENGTH_LONG).show();
        }
    }

public String createordersummary(boolean IsWhippedCream,boolean IsChocolate,String na,int price)
{

    String   priceMessage = "Name is:" + na;
       priceMessage  +=   "\nAdd WhippedCream:" + "\t" +IsWhippedCream;
       priceMessage  +=  "\nAdd Chocolate:" + " " + IsChocolate ;
    priceMessage  +=     "\nQuantity is: " +getString(R.string.quantuty);
    priceMessage  +=     "\nprice is: " +price;
       priceMessage  +=    "\n"+getString(R.string.thank_you);
    return priceMessage;
}

public int calculateprice(boolean IsWhippedCream,boolean IsChocolate) {
   int baseprice=5;
        if (IsWhippedCream) {
         baseprice = baseprice+ 1;
        }
        if (IsChocolate) {
          baseprice= baseprice + 2;
        }

    return quantity* baseprice;


}}





