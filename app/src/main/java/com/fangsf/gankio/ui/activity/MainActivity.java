package com.fangsf.gankio.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangsf.gankio.bean.RandomBean;
import com.fangsf.gankio.common.utils.DensityUtil;
import com.fangsf.gankio.di.component.AppComponent;
import com.fangsf.gankio.di.component.DaggerRandomDataComponent;
import com.fangsf.gankio.di.module.RandomDataModule;
import com.fangsf.gankio.presenter.RandomPresenter;
import com.fangsf.gankio.presenter.contract.RandomDataContract;
import com.fangsf.gankio.ui.adapter.HomePageAdapter;
import com.fangsf.minddemo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;

/**
 * @author fangsf
 */
public class MainActivity extends BaseActivity<RandomPresenter> implements RandomDataContract.IRandomDataView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.vpHome)
    ViewPager mVpHome;
    @BindView(R.id.navigationview)
    NavigationView mNavigationview;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private ImageView mIvGirl;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mPresenter.getRandomData("福利", "1");

        initToolbar();
        initTab();
        initNavigationView();
    }


    private void initNavigationView() {
        View headerView = mNavigationview.getHeaderView(0);

        mIvGirl = headerView.findViewById(R.id.ivGirl);
        TextView tvDate = headerView.findViewById(R.id.tvDate);

        String time = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        tvDate.setText(time);

        mNavigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.setting:
                        toast("设置");
                        break;

                    case R.id.collect:
                        toast("我的收藏");
                        break;

                    case R.id.recommend:
                        jumpAct(TodayRecommendActivity.class);
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.toolbar_menu);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        toggle.syncState();

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.actionSearch) {
                    toast("搜索");
                }

                return true;
            }
        });
    }

    private void initTab() {
        mVpHome.setAdapter(new HomePageAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mVpHome);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRandomDataComponent.builder().appComponent(appComponent)
                .randomDataModule(new RandomDataModule(this))
                .build().inject(this);

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String mes) {
        toast(mes);
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showData(ArrayList<RandomBean> randomBean) {
        Glide.with(this).load(randomBean.get(0).getUrl() + "?imageView2/0/w/" + DensityUtil.getScreenW(this)).error(R.mipmap.test).into(mIvGirl);
    }

}
