package com.dumai.loan.http.http;

import com.dumai.loan.http.okhttp.Config;
import com.dumai.loan.http.okhttp.OkHttpUtils;
import com.dumai.loan.jparser.JsonLoginObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： haoruigang on 2017-11-28 11:14:10.
 * 类描述：网络帮助类(主要用来管理参数)
 */
public class HttpManager {
    //    public VolleyUtils httpUtils;
    public OkHttpUtils okhttpUtils;

    private HttpManager() {
//        httpUtils = VolleyUtils.getInstance();
        okhttpUtils = OkHttpUtils.Instance();
    }

    private static class SingletonHolder {
        static HttpManager INSTANCE = new HttpManager();
    }

    public static HttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 作者： haoruigang on 2017/11/28 11:16
     * 描述: 登录
     *
     * @param tag
     * @param username
     * @param pwd
     * @param callBack
     */
    public void doLoginRequest(String tag, String username, String pwd, HttpCallBack callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("mobile", username);
        map.put("password", pwd);
        String url = Config.API.LOGIN_URL;
        okhttpUtils.postJsonOkhttpRequest(tag, url, map, callBack);
    }

    /**
     * 作者： haoruigang on 2017-12-1 14:25:06
     * 描述: 注册
     *
     * @param tag
     * @param phoneNum
     * @param pwd
     * @param callBack
     */
    public void doReginster(String tag, String phoneNum, String pwd, HttpCallBack callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("mobile", phoneNum);
        map.put("password", pwd);
        String url = Config.API.REGISTER_URL;
        okhttpUtils.postJsonOkhttpRequest(tag, url, map, callBack);
    }

    /**
     * 作者： haoruigang on 2017-12-15 10:37:05
     * 描述：忘记密码
     *
     * @param tag
     * @param phoneNum
     * @param pwd
     * @param callBack
     */
    public void doForgetpsd(String tag, String phoneNum, String pwd, HttpCallBack callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("mobile", phoneNum);
        map.put("password", pwd);
        String url = Config.API.FORGETPSD_URL;
        okhttpUtils.postJsonOkhttpRequest(tag, url, map, callBack);
    }

    /**
     * 作者： haoruigang on 2017-12-4 09:36:02
     * 描述: 身份信息提交
     *
     * @param tag
     * @param identityName
     * @param identityCode
     * @param province
     * @param city
     * @param county
     * @param detailedArs
     * @param education
     * @param maritalStatus
     * @param callBack
     */

    public void doIdentityRequest(String tag, String token, String lenderid, String mobile, String identityName, String identityCode, String province, String city, String county, String detailedArs, String education, String maritalStatus, HttpCallBack<JsonLoginObject> callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("lenderid", lenderid);
        map.put("mobile", mobile);
        map.put("lender_name", identityName);
        map.put("card_num", identityCode);
        map.put("life_province", province);
        map.put("card_city", city);
        map.put("card_coutry", county);
        map.put("card_address", detailedArs);
        map.put("education_status", education);
        map.put("maritalStatus", maritalStatus);
        String url = Config.API.IDENTITY_URL;
        okhttpUtils.postJsonOkhttpRequest(tag, url, map, callBack);
    }

    public void doJobRequest(String tag, String token, String lenderid, String mobile, String work_profession, String work_company, String income_other, HttpCallBack<JsonLoginObject> callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("lenderid", lenderid);
        map.put("mobile", mobile);
        map.put("work_profession", work_profession);
        map.put("work_company", work_company);
        map.put("income_other", income_other);
        String url = Config.API.JOB_URL;
        okhttpUtils.postJsonOkhttpRequest(tag, url, map, callBack);
    }
}


