package com.itheima.redboyclient.activities;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.TopicAdapter;
import com.itheima.redboyclient.base.BasePager;
import com.itheima.redboyclient.impl.AccountPager;
import com.itheima.redboyclient.impl.CartPager;
import com.itheima.redboyclient.impl.HomePager;
import com.itheima.redboyclient.impl.MorePager;
import com.itheima.redboyclient.impl.SearchPager;
import com.itheima.redboyclient.widget.view.NoScrollViewPager;

import org.senydevpkg.view.LoadStateLayout;

import java.util.ArrayList;

import butterknife.InjectView;

public class TopicActivity extends BaseActivity  {


    @InjectView(R.id.backTv)
    TextView mBackTv;
    @InjectView(R.id.textTitle)
    TextView mTextTitle;
    @InjectView(R.id.fraHead)
    FrameLayout mFraHead;
    @InjectView(R.id.mylimitbuy_product_list)
    ListView mMylimitbuyProductList;
    @InjectView(R.id.myfavorite_productlist_layout)
    LoadStateLayout mMyfavoriteProductlistLayout;
    @InjectView(R.id.bottomSpace)
    RelativeLayout mBottomSpace;
    @InjectView(R.id.imgHome)
    ImageView mImgHome;
    @InjectView(R.id.imgClassify)
    ImageView mImgClassify;
    @InjectView(R.id.imgSearch)
    ImageView mImgSearch;
    @InjectView(R.id.imgShoppingCar)
    ImageView mImgShoppingCar;
    @InjectView(R.id.imgMore)
    ImageView mImgMore;
    @InjectView(R.id.linToolBar)
    LinearLayout mLinToolBar;
    @InjectView(R.id.textShopCarNum)
    TextView mTextShopCarNum;
    @InjectView(R.id.rg_group)
    RadioGroup rgGroup;
    @InjectView(R.id.viewPager)
    NoScrollViewPager viewPager;
    private TopicAdapter adapter;
    private ArrayList<BasePager> pagers;
    @Override
    public void initDatas() {
        pagers = new ArrayList<>();
        pagers.add(new HomePager(this));
        pagers.add(new SearchPager(this));
        pagers.add(new CartPager(this));
        pagers.add(new AccountPager(this));
        pagers.add(new MorePager(this));
        /** 给RadioGroup 设置 是否选中的 监听事件 */
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        // 参数一: 设置当前要显示的页面, 参数二:去掉页面切换的动画
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_search:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_cart:
                        viewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_account:
                        viewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_more:
                        viewPager.setCurrentItem(4, false);
                        break;
                }
            }
        });
        //手动初始化第一个页面的数据
        pagers.get(0).initDatas();
    }
    @Override
    public void initViews() {
    }

    @Override
    protected int initContentView() {
        return R.layout.my_limit_activity;
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }


}
