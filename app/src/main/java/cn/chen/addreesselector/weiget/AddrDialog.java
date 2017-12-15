package cn.chen.addreesselector.weiget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.chen.addreesselector.DataAdapter;
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
    private List<ProvinceBean> mDataList;
    private List<String> mProvinces;
    private List<String> mCitys;
    private List<String> mAreas;
    private List<String> mTown;
    private DataAdapter mProvinceAdapter;
    private DataAdapter mCityAdapter;
    private DataAdapter mAreaAdapter;
    private DataAdapter mTownAdapter;
    public static final int PROVINCE_INDEX = 1;
    public static final int CITY_INDEX = 2;
    public static final int AREA_INDEX = 3;
    public static final int TOWN_INDEX = 4;
    private int mCurrentIndex = PROVINCE_INDEX;
    private View mRootView;


    public AddrDialog(Context context) {
        super(context, R.style.defind_Dialog);
        mContext = context;
        init();
    }

    public AddrDialog(Context context,List<ProvinceBean> dataList) {
        super(context, R.style.defind_Dialog);
        mContext = context;
        this.mDataList = dataList;
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

    public void setDataList(List<ProvinceBean> dataList) {
        this.mDataList = dataList;
    }

    public void setAddrResultListener(AddrResultListener addrResultListener) {
        this.mAddrResultListener = addrResultListener;
    }

    private void init() {
        mRootView= View.inflate(mContext, R.layout.dialog_addr, null);
        setContentView(mRootView);
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
        mProvinceAdapter = new DataAdapter();
        mCityAdapter = new DataAdapter();
        mAreaAdapter = new DataAdapter();
        mTownAdapter = new DataAdapter();
        mProvinces = getProvince(mDataList);
        mProvinceAdapter.setData(mProvinces);
        lvData.setAdapter(mProvinceAdapter);
        tvProvince.setText("请选择");
        tvProvince.setSelected(true);
        tvCity.setVisibility(View.GONE);
        tvArea.setVisibility(View.GONE);
        tvTown.setVisibility(View.GONE);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mCurrentIndex) {
                    case PROVINCE_INDEX:
                        mProvinceAdapter.setSelectPosition(position);
                        tvProvince.setText(mProvinceAdapter.getSelectName());
                        tvProvince.setSelected(false);
                        //清空数据
                        clearTabData();

                        mCitys = getCitys(mDataList, mProvinceAdapter.getSelectName());
                        if (mCitys != null && mCitys.size() > 0) {
                            mCityAdapter.setData(mCitys);
                            lvData.setAdapter(mCityAdapter);

                            mCurrentIndex = CITY_INDEX;
                            tvCity.setText("请选择");
                            tvCity.setSelected(true);
                            tvCity.setVisibility(View.VISIBLE);
                            tvArea.setVisibility(View.GONE);
                            tvTown.setVisibility(View.GONE);

                            mCityAdapter.setSelectPosition(-1);
                            mAreaAdapter.setSelectPosition(-1);
                            mTownAdapter.setSelectPosition(-1);
                            updateIndicator();
                        }

                        break;
                    case CITY_INDEX:
                        mCityAdapter.setSelectPosition(position);
                        tvCity.setText(mCityAdapter.getSelectName());
                        tvCity.setSelected(false);
                        mAreas = getAreas(mDataList,mProvinceAdapter.getSelectName(), mCityAdapter.getSelectName());
                        if (mAreas != null && mAreas.size() > 0) {
                            mAreaAdapter.setData(mAreas);
                            lvData.setAdapter(mAreaAdapter);
                            mCurrentIndex = AREA_INDEX;
                            tvArea.setText("请选择");
                            tvArea.setSelected(true);
                            tvArea.setVisibility(View.VISIBLE);
                            tvTown.setVisibility(View.GONE);
                            updateIndicator();
                        }
                        break;
                    case AREA_INDEX:
                        mAreaAdapter.setSelectPosition(position);
                        tvArea.setText(mAreaAdapter.getSelectName());
                        tvArea.setSelected(false);
                        mTown = getTowns(mDataList,mProvinceAdapter.getSelectName(), mCityAdapter.getSelectName(),mAreaAdapter.getSelectName());
                        if (mTown != null && mTown.size() > 0) {
                            mTownAdapter.setData(mTown);
                            lvData.setAdapter(mTownAdapter);
                            mCurrentIndex = TOWN_INDEX;
                            tvTown.setText("请选择");
                            tvTown.setSelected(true);
                            tvTown.setVisibility(View.VISIBLE);
                            updateIndicator();
                        }
                        break;
                    case TOWN_INDEX:
                        mTownAdapter.setSelectPosition(position);
                        tvTown.setText(mTownAdapter.getSelectName());
                        mCurrentIndex = TOWN_INDEX;
                        updateIndicator();
                        break;
                }
            }
        });
    }

    private void clearTabData() {
        mCitys = null;
        mAreas = null;
        mTown = null;

        mCityAdapter.setData(mCitys);
        mAreaAdapter.setData(mAreas);
        mTownAdapter.setData(mTown);
    }

    private List<String> getProvince(List<ProvinceBean> dataList) {
        List<String> provinces = new ArrayList<>();
        for (ProvinceBean bean : dataList) {
            provinces.add(bean.getName());
        }
        return provinces;
    }

    private List<String> getCitys(List<ProvinceBean> dataList,String provinceName) {
        List<String> citys = new ArrayList<>();
        for (ProvinceBean bean : dataList) {
            if (provinceName.equals(bean.getName())){
                List<ProvinceBean.CityBean> cityList = bean.getCityList();
                for (ProvinceBean.CityBean cityBean: cityList) {
                    citys.add(cityBean.getName());
                }
            }
        }
        return citys;
    }

    private List<String> getAreas(List<ProvinceBean> dataList,String provinceName,String cityName) {
        List<String> areas = new ArrayList<>();
        for (ProvinceBean bean : dataList) {
            if (provinceName.equals(bean.getName())) {
                List<ProvinceBean.CityBean> cityList = bean.getCityList();
                for (ProvinceBean.CityBean cityBean : cityList) {
                    if (cityName.equals(cityBean.getName())) {
                        areas = cityBean.getArea();
                    }
                }
            }
        }
        return areas;
    }

    private List<String> getTowns(List<ProvinceBean> dataList,String provinceName,String cityName,String areaName) {
        List<String> towns = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            towns.add(areaName + i);
        }
        return towns;
    }

    private void updateIndicator() {
        mRootView.post(new Runnable() {
            @Override
            public void run() {
                switch (mCurrentIndex) {
                    case PROVINCE_INDEX:
                        buildIndicatorAnimatorTowards(tvProvince).start();
                        break;
                    case CITY_INDEX:
                        buildIndicatorAnimatorTowards(tvCity).start();
                        break;
                    case AREA_INDEX:
                        buildIndicatorAnimatorTowards(tvArea).start();
                        break;
                    case TOWN_INDEX:
                        buildIndicatorAnimatorTowards(tvTown).start();
                        break;
                }
            }
        });
    }

    private AnimatorSet buildIndicatorAnimatorTowards(TextView tab) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewIndicator, "X", viewIndicator.getX(), tab.getX());
        final ViewGroup.LayoutParams layoutParams = viewIndicator.getLayoutParams();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.width, tab.getMeasuredWidth());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.width = (int) animation.getAnimatedValue();
                viewIndicator.setLayoutParams(layoutParams);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.playTogether(objectAnimator, valueAnimator);

        return set;
    }

    @OnClick({R.id.tv_cancle,R.id.tv_sure,R.id.tv_province, R.id.tv_city, R.id.tv_area, R.id.tv_town})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                dismiss();
                break;
            case R.id.tv_sure:
                if (mAddrResultListener != null) {
                    String provinceName = mProvinceAdapter.getSelectName();
                    String cityName = mCityAdapter.getSelectName();
                    String areaName = mAreaAdapter.getSelectName();
                    String townName = mTownAdapter.getSelectName();
                    mAddrResultListener.onAddrResult(provinceName, cityName, areaName, townName);
                }
                dismiss();
                break;
            case R.id.tv_province:
                mCurrentIndex = PROVINCE_INDEX;
                lvData.setAdapter(mProvinceAdapter);
                updateIndicator();
                break;
            case R.id.tv_city:
                mCurrentIndex = CITY_INDEX;
                lvData.setAdapter(mCityAdapter);
                updateIndicator();
                break;
            case R.id.tv_area:
                mCurrentIndex = AREA_INDEX;
                lvData.setAdapter(mAreaAdapter);
                updateIndicator();
                break;
            case R.id.tv_town:
                mCurrentIndex = TOWN_INDEX;
                lvData.setAdapter(mTownAdapter);
                updateIndicator();
                break;
        }
    }

    public interface AddrResultListener {
        void onAddrResult(String province, String city, String area, String town);
    }


}
