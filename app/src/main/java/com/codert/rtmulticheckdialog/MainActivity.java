package com.codert.rtmulticheckdialog;

import android.app.Activity;
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
    //准备选项列表数据
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
        Activity context = MainActivity.this;

        //初始化dialog相关属性
        //new RTMultiCheckDialog(context）为默认dialog宽高屏幕占比0.7
        RTMultiCheckDialog customDialog = new RTMultiCheckDialog(context,0.7,0.7)
                .setTitleText("请选择")
                .setIcon(R.drawable.image)
                .setConfirmText("确定")
                .setCancelText("取消")
                .setConfirmOnclicListener(new RTMultiCheckDialog.OnMultiCheckClickListener() {
                    @Override
                    public void onClick(RTMultiCheckDialog rtMultiCheckDialog) {
                        //点击确认事件
                        rtMultiCheckDialog.dismiss();
                    }
                })
                .setCancelOnclicListener(new RTMultiCheckDialog.OnMultiCheckClickListener() {
                    @Override
                    public void onClick(RTMultiCheckDialog rtMultiCheckDialog) {
                        //点击取消事件
                        rtMultiCheckDialog.dismiss();
                    }
                })
                .setItemNames(itemList);

        //其他设置
        //设置点击空白处是否关闭dialog，默认不关闭
//        customDialog.setCancelInOutside(true);
        //设置图标是否显示，默认显示
//        customDialog.setIconShow(false);

        //显示dialog
        customDialog.show();

    }
}
