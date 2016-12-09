package com.venus.client;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.venus.frame.constants.Constants;

public class SmsClient {
	public static SingleSendSmsResponse send(JSONObject sendInfo) throws Exception {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", Constants.ACCESS_KEY_ID,
				Constants.ACCESS_KEY_SECRET);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendSmsRequest request = new SingleSendSmsRequest();
		request.setSignName(Constants.SMS_SIGN_NAME);
		request.setTemplateCode(Constants.SMS_TEMPLATE_CODE);
		request.setParamString("{}");
		request.setRecNum("接收号码");
		SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
		return httpResponse;
	}
}
