package com.codert.rtmulticheckdialog_module;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RTMultiCheckDialog extends Dialog {
    //UI
    private View mDialogView;
    private TextView titleText;//标题
    private Button mConfirmButton;//确定
    private Button mCancelButton;//取消
    private ImageView icon;//标题图片
    private ListView itemList;//选项列表

    //Constant
    private static double default_widthScale = 0.7; // 默认宽度
    private static double default_heightScale = 0.7; // 默认高度

    //Variable
    private List<String> itemNames;//选项内容
    private List<Boolean> itemChecked;//选项是否选定
    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

    //Object
    private Activity context;
    private OnMultiCheckClickListener mCancelClickListener;//取消回调
    private OnMultiCheckClickListener mConfirmClickListener;//确认回调

    public RTMultiCheckDialog(Activity context) {
        this(context, default_widthScale, default_heightScale);
    }

    public RTMultiCheckDialog(Activity context, double widthScale, double heightScale) {
        super(context, R.style.RTMultiCheckDialog);
        mDialogView = LayoutInflater.from(context).inflate(R.layout.multicheck_dialog, null);
        setContentView(mDialogView);
        this.context = context;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int windowWidth = outMetrics.widthPixels;
        int windowHeight = outMetrics.heightPixels;

        //设置dialog占屏幕比例
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (windowWidth * widthScale); // 宽度设置为屏幕的一定比例大小
        params.height = (int) (windowHeight * heightScale);
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);

        //点击空白默认不可取消
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        //初始化
        itemNames = new ArrayList<>();
        itemChecked = new ArrayList<>();
        icon = (ImageView)mDialogView.findViewById(R.id.icon);
        titleText = ((TextView)mDialogView.findViewById(R.id.title));
        itemList = (ListView)mDialogView.findViewById(R.id.list_item);
        mConfirmButton = (Button)mDialogView.findViewById(R.id.confirm);
        mCancelButton = (Button)mDialogView.findViewById(R.id.cancel);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfirmClickListener != null) {
                    mConfirmClickListener.onClick(RTMultiCheckDialog.this);
                } else {
                    dismiss();
                }
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancelClickListener != null) {
                    mCancelClickListener.onClick(RTMultiCheckDialog.this);
                } else {
                    dismiss();
                }
            }
        });
    }

    //设置标题图标
    public RTMultiCheckDialog setIcon(int resourceId){
        icon.setImageResource(resourceId);
        return this;
    }
    //是否显示标题图标
    public void setIconShow(boolean show){
        if(show){
            icon.setVisibility(View.VISIBLE);
        }else {
            icon.setVisibility(View.GONE);
        }
    }

    //设置标题
    public RTMultiCheckDialog setTitleText(String title){
        titleText.setText(title);
        return this;
    }

    //设置确定按钮文字
    public RTMultiCheckDialog setConfirmText(String confirmText){
        mConfirmButton.setText(confirmText);
        return this;
    }

    //设置确定按钮回调
    public RTMultiCheckDialog setConfirmOnclicListener(OnMultiCheckClickListener listener){
        mConfirmClickListener = listener;
        return this;
    }

    //设置取消按钮文字
    public RTMultiCheckDialog setCancelText(String cancelText){
        mCancelButton.setText(cancelText);
        return this;
    }

    //设置取消按钮回调
    public RTMultiCheckDialog setCancelOnclicListener(OnMultiCheckClickListener listener){
        mCancelClickListener = listener;
        return this;
    }

    public static interface OnMultiCheckClickListener {
        public void onClick(RTMultiCheckDialog rtMultiCheckDialog);
    }

    //设置选项列表显示
    public RTMultiCheckDialog setItemNames(List<String> itemNames) {
        this.itemNames = itemNames;
        for(int i = 0;i<itemNames.size();i++){
            itemChecked.add(false);
        }
        for (int i = 0; i < itemNames.size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("text", itemNames.get(i));
            mData.add(item);
        }
        itemList.setAdapter(new MultiSimpleAdapter());
        return this;
    }

    //设置点击空白是否取消
    public RTMultiCheckDialog setCancelInOutside(Boolean cancelable){
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
        return this;
    }

    //获取点选结果集
    public List<Boolean> getItemChecked() {
        return itemChecked;
    }

    private class MultiSimpleAdapter extends BaseAdapter {
        private CheckBox checkBox;
        private TextView textView;

        public MultiSimpleAdapter() {
        }

        @Override
        public int getCount() {
            return itemNames.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LinearLayout.inflate(context, R.layout.item_check, null);
            }
            textView = (TextView) convertView.findViewById(R.id.name);
            textView.setText(itemNames.get(position));

            checkBox = (CheckBox) convertView.findViewById(R.id.check);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemChecked.set(position,!itemChecked.get(position));
                }
            });

            if (itemChecked.get(position) == true) {
                checkBox.setChecked(true);
            } else if (itemChecked.get(position) == false) {
                checkBox.setChecked(false);
            }
            return convertView;
        }
    }
}
