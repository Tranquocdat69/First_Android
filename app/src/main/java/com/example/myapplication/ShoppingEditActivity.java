package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShoppingEditActivity extends AppCompatActivity {

    private Date mDateBought;
    private EditText mEditTextTitle;
    private EditText mEditTextCount;
    private EditText mEditTextPrice;
    private EditText mEditTextDate;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_edit);
        mEditTextTitle = findViewById(R.id.txtTitle);
        mEditTextCount = findViewById(R.id.txtCount);
        mEditTextPrice = findViewById(R.id.txtPrice);
        mEditTextDate = findViewById(R.id.txtDate);
        mId= getIntent().getExtras().getInt("idsp");
        IShoppingDAO dao = new ImplementShoppingDAO(this);
        Shopping s = dao.selectById(mId);
        if (s != null){
            mEditTextTitle.setText(s.getName());
            mEditTextCount.setText(s.getCount()+"");
            mEditTextPrice.setText(s.getPrice()+"");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            mEditTextDate.setText(fmt.format(s.getDateBought()));
        }
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

    public void actionEdit(View view) {
        try {
        String name = mEditTextTitle.getText().toString();
        int count = Integer.parseInt(mEditTextCount.getText().toString());
        double price = Double.parseDouble(mEditTextPrice.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBought = simpleDateFormat.parse(mEditTextDate.getText().toString());

            Shopping s = new Shopping(mId,name,count,price,dateBought);
            IShoppingDAO dao = new ImplementShoppingDAO(this);
            boolean isOk = dao.update(mId, s);
            if (isOk){
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                Intent shoppingList = new Intent(this,ShoppingListActivity.class);
                startActivity(shoppingList);
            }else{
                Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}