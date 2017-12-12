package com.dumai.loan.view.pickers;

import com.dumai.loan.R;
import com.dumai.loan.util.ResourcesUtils;
import com.dumai.loan.view.pickers.common.LineConfig;
import com.dumai.loan.view.pickers.listeners.OnItemPickListener;
import com.dumai.loan.view.pickers.listeners.OnSingleWheelListener;
import com.dumai.loan.view.pickers.picker.SinglePicker;

/**
 * 名称：CashLoan
 * 描述：紧急联系人单选
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/27 13:55
 */
public class SinglePickContarts {

    private static SinglePickContarts singlePickContarts;
    private LineConfig config;

    public static SinglePickContarts getIntance() {
        if (singlePickContarts == null) {
            singlePickContarts = new SinglePickContarts();
        }
        return singlePickContarts;
    }

    public void Singlepicker(SinglePicker<String> picker, final SingleItem singleItem, String selectedItem, int selectedIndex) {
        picker.setCanLoop(true);//禁用循环
        picker.setTopBackgroundColor(ResourcesUtils.getColor(R.color.white));
        picker.setTopHeight(50);
        picker.setTopLineColor(ResourcesUtils.getColor(R.color.gainsboro));
        picker.setTopLineHeight(1);
        picker.setCancelText("取\t消");
        picker.setCancelTextColor(ResourcesUtils.getColor(R.color.black_1));
        picker.setCancelTextSize(15);
        picker.setSubmitText("确\t定");
        picker.setSubmitTextColor(ResourcesUtils.getColor(R.color.black_1));
        picker.setSubmitTextSize(15);
        picker.setSelectedTextColor(ResourcesUtils.getColor(R.color.black_1));
        picker.setUnSelectedTextColor(ResourcesUtils.getColor(R.color.back_4));
        config = new LineConfig();
        config.setColor(ResourcesUtils.getColor(R.color.black_1));//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(600);
        picker.setHeight(380);
        picker.setBackgroundColor(ResourcesUtils.getColor(R.color.gainsboro1));
        picker.setSelectedItem(selectedItem);
        picker.setSelectedIndex(selectedIndex);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                singleItem.onItemPicked(index, item);
            }
        });
        picker.setOnSingleWheelListener(new OnSingleWheelListener() {
            @Override
            public void onWheeled(int index, String item) {
                singleItem.onItemPicked(index, item);
            }
        });
        picker.show();
    }

}
