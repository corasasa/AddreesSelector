package cn.chen.addreesselector.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chenjinglan on 2017/12/13.
 * email:13925024285@163.com
 */

public class FileUtil {

    public static String getAssetsJson(Context context,String fileName){
        StringBuilder sBuilder=new StringBuilder();
        try {
            InputStream in = context.getAssets().open(fileName);
            BufferedReader reader =new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line=reader.readLine()) != null) {
                sBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sBuilder.toString();
    }
}
