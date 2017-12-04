package cn.chen.addreesselector.weiget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.chen.addreesselector.R;
import cn.chen.addreesselector.utils.UiUtil;

/**
 * Created by chenjinglan on 2017/12/4.
 * email:13925024285@163.com
 */

public class AddrDialog extends Dialog {
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_town)
    TextView tvTown;
    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    @BindView(R.id.view_indicator)
    View viewIndicator;
    @BindView(R.id.lv_data)
    ListView lvData;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    private Context mContext;
    private AddrResultListener mAddrResultListener;


    public AddrDialog(Context context) {
        super(context, R.style.defind_Dialog);
        mContext = context;
        init();
    }

    public AddrDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected AddrDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public void setAddrResultListener(AddrResultListener addrResultListener) {
        this.mAddrResultListener = addrResultListener;
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.dialog_addr, null);
        setContentView(view);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = UiUtil.dpToPx(mContext, 256);
        window.setAttributes(params);
        initContent();
    }

    private void initContent() {

    }

    @OnClick({R.id.tv_province, R.id.tv_city, R.id.tv_area, R.id.tv_town})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                dismiss();
                break;
            case R.id.tv_sure:
                if (mAddrResultListener != null) {
                    mAddrResultListener.onAddrResult("", "", "", "");
                }
                dismiss();
                break;
            case R.id.tv_province:
                break;
            case R.id.tv_city:
                break;
            case R.id.tv_area:
                break;
            case R.id.tv_town:
                break;
        }
    }

    interface AddrResultListener {
        void onAddrResult(String province, String city, String area, String town);
    }


}
