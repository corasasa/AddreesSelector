package cn.chen.addreesselector.utils;

import android.content.Context;

/**
 * Created by chenjinglan on 2017/12/4.
 * email:13925024285@163.com
 */

public class UiUtil {
    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) Math.ceil(density * dp);
    }
}
