package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {
    List<Shopping> list;
    private ShoppingListActivity _there = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        loadData();
    }

    private void loadData(){
        IShoppingDAO dao = new ImplementShoppingDAO(this);
        list = dao.selectAll();
        AdapterShopping adapter = new AdapterShopping(this,R.layout.item_shopping,list);
        ListView listView = findViewById(R.id.formListView);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idMenu = item.getItemId();
        switch (idMenu){
            case R.id.menu_add:
                Intent shoppingAdd = new Intent(this,ShoppingAddActivity.class);
                startActivity(shoppingAdd);
                break;
            default:
                Toast.makeText(this,"Chức năng đang phát triển",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_shopping,menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Shopping s = list.get(info.position);

        menu.setHeaderTitle(s.getName());

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int idMenu = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Shopping s = list.get(info.position);
        switch (idMenu){
            case R.id.menu_edit:
                Intent shoppingEdit = new Intent(this,ShoppingEditActivity.class);
                shoppingEdit.putExtra("idsp",s.getId());
                startActivity(shoppingEdit);
                break;
            case R.id.menu_delete:
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                IShoppingDAO dao = new ImplementShoppingDAO(_there);
                                boolean isOk = dao.delete(s.getId());
                                if (isOk){
                                    Toast.makeText(_there, "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                    loadData();
                                }else{
                                    Toast.makeText(_there, "Xóa dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                                }
                                //your deleting code
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                //to do
                break;
            default:
                Toast.makeText(this,"Chức năng đang phát triển",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        loadData();
        super.onResume();
    }
}