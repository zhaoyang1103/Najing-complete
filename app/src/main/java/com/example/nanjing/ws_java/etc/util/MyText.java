package com.example.nanjing.ws_java.etc.util;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyText extends android.support.v7.widget.AppCompatTextView {
    public MyText(Context context) {
        super(context);
    }

    public MyText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
