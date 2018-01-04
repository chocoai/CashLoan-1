package com.dumai.loan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dumai.loan.R;
import com.dumai.loan.base.BaseActivity;
import com.dumai.loan.http.http.HttpCallBack;
import com.dumai.loan.http.http.HttpManager;
import com.dumai.loan.jparser.JsonLoginObject;
import com.dumai.loan.util.EmptyUtils;
import com.dumai.loan.util.LogUtil;
import com.dumai.loan.util.SharedUtils;
import com.dumai.loan.util.ToastUtils;
import com.dumai.loan.util.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 忘记密码
 * haoruigang
 * 2017-11-27 19:47:27
 */
public class ForgetPsdActivity extends BaseActivity {

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_forget_psd)
    Button btnForgetPsd;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_forget_psd;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("忘记密码");
        toolbarHelper.setLeftMenuTitle("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void init() {
        super.init();

    }


    private String phoneNum, passWord;

    private void setForgetPsd() {
        if (validate()) {
            HttpManager.getInstance().doForgetpsd("ForgetPsdActivity", phoneNum, passWord, new HttpCallBack<JsonLoginObject>(ForgetPsdActivity.this, true) {
                @Override
                public void onError(Throwable throwable) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SharedUtils.putBoolean(ForgetPsdActivity.this, "LoginSuccess", false);
                            ToastUtils.showToastInThread("网络不可用!");
                        }
                    });
                }

                @Override
                public void onSuccess(JsonLoginObject date) {
                    if (date != null) {
                        final JsonLoginObject.ResultBean Value = date.getResult();
                        if (Value.getCode().equals("1")) {
                            JsonLoginObject.ResultBean.DataBean dataBean = Value.getData();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToastInThread("修改密码成功!");
                                }
                            });
                            startActivity(new Intent(ForgetPsdActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToastInThread("修改密码失败");
                                }
                            });
                        }
                        LogUtil.i(ForgetPsdActivity.this, "修改密码" + Value.getMsg());
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToastInThread("网络不可用!");
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * 非空验证
     *
     * @return
     */
    private boolean validate() {
        phoneNum = etPhoneNumber.getText().toString().trim();
        passWord = etPassword.getText().toString().trim();
        if (!EmptyUtils.isNotEmpty(phoneNum)) {
            ToastUtils.showToast("账号为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(passWord)) {
            ToastUtils.showToast("密码为空!");
            return false;
        }
        return true;
    }

    @OnClick(R.id.btn_forget_psd)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_psd:
                setForgetPsd();
                break;
        }
    }
}
