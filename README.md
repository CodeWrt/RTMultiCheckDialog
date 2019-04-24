# RTMultiCheckDialog
[![](https://jitpack.io/v/CodeWrt/RTMultiCheckDialog.svg)](https://jitpack.io/#CodeWrt/RTMultiCheckDialog)

自定义多选对话框控件。
## Preview
![自定义多选框展示](https://github.com/CodeWrt/RTMultiCheckDialog/blob/master/images/dialog.jpg)
## Gradle Dependency
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
    ...
    implementation 'com.github.CodeWrt:RTMultiCheckDialog:1.2'
}
```
## Usage
```
        Activity context = MainActivity.this;
        //准备选项列表数据
        List<String> itemList;
        itemList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            itemList.add("选项" + i);
        }
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
        //显示dialog
        customDialog.show();
```
```
        //其他设置
        //设置点击空白处是否关闭dialog，默认不关闭
        customDialog.setCancelInOutside(true);
        //设置图标是否显示，默认显示
        customDialog.setIconShow(false);
```
