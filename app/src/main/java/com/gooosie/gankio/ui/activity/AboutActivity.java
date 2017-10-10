package com.gooosie.gankio.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gooosie.gankio.BuildConfig;
import com.gooosie.gankio.R;
import com.gooosie.gankio.ui.base.ToolBarActivity;
import com.gooosie.gankio.view.IAboutView;

import butterknife.BindView;

public class AboutActivity extends ToolBarActivity{

    @BindView(R.id.about_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String version = "v" + BuildConfig.VERSION_NAME;
        tvVersion.setText(version);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initPresenter() {

    }
}
