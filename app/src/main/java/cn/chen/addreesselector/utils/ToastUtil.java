package cn.chen.addreesselector.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chenjinglan on 2017/12/4.
 * email:13925024285@163.com
 */

public class ToastUtil {
    public static void showString(Context context, String tip) {
        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();
    }
}
