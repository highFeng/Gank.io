package com.fangsf.gankio.ui.fragment.hometab;

import com.fangsf.gankio.di.component.AppComponent;
import com.fangsf.gankio.di.component.DaggerDatasComponent;
import com.fangsf.gankio.di.module.DatasModule;

/**
 * Created by fangsf on 2018/2/1.
 * Useful: 瞎推荐
 */

public class BlindRecommendFragment extends DatasFragment {
    @Override
    protected int loadCount() {
        return 10;
    }

    @Override
    protected String type() {
        return "瞎推荐";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerDatasComponent.builder().appComponent(appComponent)
                .datasModule(new DatasModule(this))
                .build().inject(this);
    }
}
