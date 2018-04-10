package com.aspectj.combizlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aspectj.aspectjaplay.activitylife.ActivityOnCreate;
import com.aspectj.aspectjaplay.activitylife.ActivityOnDestroy;
import com.aspectj.aspectjaplay.activitylife.ActivityOnPause;
import com.aspectj.aspectjaplay.activitylife.ActivityOnRestart;
import com.aspectj.aspectjaplay.activitylife.ActivityOnResume;
import com.aspectj.aspectjaplay.activitylife.ActivityOnStart;
import com.aspectj.aspectjaplay.activitylife.ActivityOnStop;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> @author:      谢文良
 * <br> Date:        2018/4/3 15:05
 */
public class BaseActivity extends AppCompatActivity {

    @ActivityOnCreate
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateLife(savedInstanceState);
    }

    /**
     * <br> Description: 替代生命周期onCreate
     * <br> Author:      xwl
     * <br> Date:        2018/4/8 10:02
     *
     * @param savedInstanceState savedInstanceState
     */
    protected void onCreateLife(@Nullable Bundle savedInstanceState) {
    }

    @ActivityOnRestart
    @Override
    protected final void onRestart() {
        super.onRestart();
        onRestartLife();
    }

    /**
     * <br> Description: 替代生命周期onRestart
     * <br> Author:      xwl
     * <br> Date:        2018/4/8 10:02
     */
    protected void onRestartLife() {
    }

    @ActivityOnStart
    @Override
    protected final void onStart() {
        super.onStart();
        onStartLife();
    }

    /**
     * <br> Description: 替代生命周期onStart
     * <br> Author:      xwl
     * <br> Date:        2018/4/8 10:03
     */
    protected void onStartLife() {
    }

    @ActivityOnResume
    @Override
    protected final void onResume() {
        super.onResume();
        onResumeLife();
    }

    /**
     * <br> Description: 替代生命周期onResume
     * <br> Author:      xwl
     * <br> Date:        2018/4/8 10:03
     */
    protected void onResumeLife() {
    }

    @ActivityOnPause
    @Override
    protected final void onPause() {
        super.onPause();
        onPauseLife();
    }

    /**
     * <br> Description: 替代生命周期onPause
     * <br> Author:      xwl
     * <br> Date:        2018/4/8 10:03
     */
    protected void onPauseLife() {
    }

    @ActivityOnStop
    @Override
    protected final void onStop() {
        super.onStop();
        onStopLife();
    }

    /**
     * <br> Description: 替代生命周期onStop
     * <br> Author:      xwl
     * <br> Date:        2018/4/8 10:03
     */
    protected void onStopLife() {
    }

    @ActivityOnDestroy
    @Override
    protected final void onDestroy() {
        super.onDestroy();
        onDestroyLife();
    }

    /**
     * <br> Description: 替代生命周期onDestroy
     * <br> Author:      xwl
     * <br> Date:        2018/4/8 10:03
     */
    protected void onDestroyLife() {
    }
}
