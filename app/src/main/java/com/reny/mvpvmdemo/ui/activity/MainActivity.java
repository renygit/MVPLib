package com.reny.mvpvmdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.api.APIConfig;
import com.reny.mvpvmdemo.core.MyBaseActivity;
import com.reny.mvpvmdemo.databinding.ActivityMainBinding;
import com.reny.mvpvmdemo.entity.event.RvScrollEvent;
import com.reny.mvpvmdemo.ui.fragment.FragmentA;
import com.reny.mvpvmdemo.utils.ResUtils;
import com.reny.mvpvmlib.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyBaseActivity<ActivityMainBinding> {

    private String[] tabTitles = ResUtils.getArrStr(R.array.tabTitles);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
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
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra("url", APIConfig.ABOUT_URL);
                        startActivity(intent);
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
        binding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //list回到顶部
                int tabIndex = binding.vp.getCurrentItem();
                EventBus.getDefault().post(new RvScrollEvent(tabIndex, 0));
            }
        });

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        binding.tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
