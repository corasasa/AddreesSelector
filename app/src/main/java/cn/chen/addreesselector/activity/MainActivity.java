package cn.chen.addreesselector.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.chen.addreesselector.R;
import cn.chen.addreesselector.utils.ToastUtil;
import cn.chen.addreesselector.weiget.AddrDialog;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_select)
    Button btnSelect;
    @BindView(R.id.tv_select_res)
    TextView tvSelectRes;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_select)
    public void onViewClicked() {
        ToastUtil.showString(this, "btn_select");
        AddrDialog addrDialog = new AddrDialog(this);
        addrDialog.show();
    }
}
