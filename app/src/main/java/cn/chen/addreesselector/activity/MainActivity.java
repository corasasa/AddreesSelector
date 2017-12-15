package cn.chen.addreesselector.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.chen.addreesselector.R;
import cn.chen.addreesselector.utils.FileUtil;
import cn.chen.addreesselector.weiget.AddrDialog;
import cn.chen.addreesselector.weiget.ProvinceBean;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_select)
    Button btnSelect;
    @BindView(R.id.tv_select_res)
    TextView tvSelectRes;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    List<ProvinceBean> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    @OnClick(R.id.btn_select)
    public void onViewClicked() {
        AddrDialog addrDialog = new AddrDialog(this, mDataList);
        addrDialog.setAddrResultListener(new AddrDialog.AddrResultListener() {
            @Override
            public void onAddrResult(String province, String city, String area, String town) {
                tvSelectRes.setText(province + city + area + town);
            }
        });
        addrDialog.show();
    }

    public void initData() {
        String assetsJson = FileUtil.getAssetsJson(this, "province.json");
        Type type = new TypeToken<List<ProvinceBean>>() {
        }.getType();
        mDataList = new Gson().fromJson(assetsJson, type);
        getProvince(mDataList);
    }

    public void getProvince(List<ProvinceBean> dataList) {
        List<String> provinces = new ArrayList<>();
        for (ProvinceBean bean : dataList) {
            provinces.add(bean.getName());
        }
    }
}
