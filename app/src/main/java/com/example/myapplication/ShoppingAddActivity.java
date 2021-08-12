package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShoppingAddActivity extends AppCompatActivity {

    private Date mDateBought;
    private EditText mEditTextTitle;
    private EditText mEditTextCount;
    private EditText mEditTextPrice;
    private EditText mEditTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextTitle = findViewById(R.id.txtTitle);
        mEditTextCount = findViewById(R.id.txtCount);
        mEditTextPrice = findViewById(R.id.txtPrice);
        mEditTextDate = findViewById(R.id.txtDate);

    }

    public void openDatePicker(View view) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,dayOfMonth);
                mDateBought = calendar.getTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                mEditTextDate.setText(simpleDateFormat.format(mDateBought));
            }
        };
        Calendar c = Calendar.getInstance();
        DatePickerDialog pickerDialog = new DatePickerDialog(this,listener,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        pickerDialog.show();
    }



    public void actionAdd(View view) {
         String name = mEditTextTitle.getText().toString();
         int count = Integer.parseInt(mEditTextCount.getText().toString());
         double price = Double.parseDouble(mEditTextPrice.getText().toString());
         Date dateBought = mDateBought;

         Shopping s = new Shopping(name,count,price,dateBought);
        IShoppingDAO dao = new ImplementShoppingDAO(this);
        boolean isOk = dao.insert(s);
        if (isOk){
            Toast.makeText(this,"Thêm mới dữ liệu thành công",Toast.LENGTH_LONG).show();
            Intent shoppingList = new Intent(this,ShoppingListActivity.class);
            startActivity(shoppingList);
        }else{
            Toast.makeText(this,"Thêm mới dữ liệu thất bại",Toast.LENGTH_LONG).show();
        }
    }
}