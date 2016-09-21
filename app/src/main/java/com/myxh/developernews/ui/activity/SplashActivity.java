package com.myxh.developernews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.myxh.developernews.R;
import com.myxh.developernews.ui.base.BaseActivity;
import com.myxh.developernews.util.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_img)
    ImageView mSplashImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation
                .RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startToMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mSplashImg.startAnimation(scaleAnimation);
    }

    private void startToMainActivity() {
        if (NetworkUtil.isNetConnected(this)) {
            openActivityWithOutAnim(MainActivity.class);
            finish();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle(R.string.tips);
            builder.setMessage(R.string.network_not_conntected);
            builder.setPositiveButton(R.string.dialog_ok, (dialog, which) -> {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                finish();
            });
            builder.setNegativeButton(R.string.dialog_cancel, (dialog, which) -> {
                dialog.cancel();
                finish();
            });
            builder.create();
            builder.show();
        }
    }


}
