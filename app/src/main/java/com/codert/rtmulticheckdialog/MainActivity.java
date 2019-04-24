package com.codert.rtmulticheckdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codert.rtmulticheckdialog_module.RTMultiCheckDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initUI();


    }
    private void initData(){
        itemList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            itemList.add("选项" + i);
        }
    }

    private void initUI(){
        findViewById(R.id.showCustomDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCustomDialog();
            }
        });
    }

    private void initCustomDialog() {
        RTMultiCheckDialog customDialog = new RTMultiCheckDialog(MainActivity.this)
                .setTitleText("请选择")
                .setIcon(R.drawable.image)
                .setConfirmText("确定")
                .setCancelText("取消")
                .setConfirmOnclicListener(new RTMultiCheckDialog.OnMultiCheckClickListener() {
                    @Override
                    public void onClick(RTMultiCheckDialog rtMultiCheckDialog) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                        rtMultiCheckDialog.dismiss();
                    }
                })
                .setCancelOnclicListener(new RTMultiCheckDialog.OnMultiCheckClickListener() {
                    @Override
                    public void onClick(RTMultiCheckDialog rtMultiCheckDialog) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                        rtMultiCheckDialog.dismiss();
                    }
                })
                .setItemNames(itemList);
        customDialog.show();
    }
}
