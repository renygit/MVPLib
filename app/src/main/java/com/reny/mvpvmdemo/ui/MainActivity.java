package com.reny.mvpvmdemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseActivity;
import com.reny.mvpvmdemo.databinding.ActivityMainBinding;
import com.reny.mvpvmdemo.presenter.MainPresenter;
import com.reny.mvpvmdemo.ui.fragment.FragmentA;
import com.reny.mvpvmdemo.utils.ResUtils;
import com.reny.mvpvmdemo.utils.ToastUtils;
import com.reny.mvpvmlib.base.BasePresenter;
import com.reny.mvpvmlib.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyBaseActivity<ActivityMainBinding> {

    private MainPresenter presenter;

    private String[] tabTitles = ResUtils.getArrStr(R.array.tabTitles);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter getPresenter() {
        if(null == presenter){
            presenter = new MainPresenter(this, new BaseViewModel());
        }
        return presenter;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding.toolbar.setTitle(tabTitles[0]);
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        ToastUtils.showShort("menu...");
                        /*Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra("url", APIConfig.ABOUT_URL);
                        startActivity(intent);*/
                        break;
                }
                return true;
            }
        });

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentA());
        binding.vp.setOffscreenPageLimit(fragmentList.size());
        //we need the savedInstanceState to retrieve the position
        binding.tabLayout.initialize(binding.vp, getSupportFragmentManager(), fragmentList, savedInstanceState);

        //演示“发送事件” （功能可以用FragmentA的实例调用内部方法实现滑动到顶部，eg: fragmentA.scrollToTop(); 在FragmentA中实现滚动方法即可）
        /*binding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //list回到顶部
                switch (binding.vp.getCurrentItem()){
                    case 0://点击toolbar时当前页面在第1页时
                        RxBus.get().send(new RvScrollEvent(FAScrollType, 0));
                        break;
                    case 1://点击toolbar时当前页面在第2页时
                        RxBus.get().send(new RvScrollEvent(FBScrollType, 0));
                        break;
                    case 2://点击toolbar时当前页面在第3页时
                        RxBus.get().send(new RvScrollEvent(FCScrollType, 0));
                        break;
                }
            }
        });*/

        binding.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                binding.toolbar.setTitle(tabTitles[position]);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
