package com.beigebigdata.bdCourt.ca.common.service.impl;

import com.beigebigdata.bdCourt.ca.common.service.MakerCodeService;
import com.septinary.common.util.DateTimeUtil;
import com.septinary.common.util.RandomUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("makerCodeService")
public class MakerCodeServiceImpl implements MakerCodeService {


	@Override
	public String makeCode(String tag) {
		return this.makeCode(tag, null);
	}

	@Override
	public String makeCode(String tag, HashMap<String,Object> options) {
		// TODO Auto-generated method stub
		// return null;
		String code = "";
		
		switch(tag) {
		case "MemberCode":
		{
			code = "M" + DateTimeUtil.NowMS() + RandomUtil.Number(4);
		}
		break;
		case "SNSAccountCode":
		{
			code = "SNS-" + RandomUtil.Number(4) + "-" + DateTimeUtil.NowMS();
		}
		break;
		case "ChatAccountCode":
		{
			code = "CHAT-" + RandomUtil.Number(4) + "-" + DateTimeUtil.NowMS();
		}
		break;
		case "CaseCode":
		{
			code = "C" + DateTimeUtil.Now("yyyyMMddHHmmssSSS") + "-" + RandomUtil.Word(6);
		}
		break;
		case "TrendBodySN":
		{
			code = "TB-" + DateTimeUtil.NowMS() + "-" + RandomUtil.Word(6);
		}
		break;
		case "CaseEvidence":
		{
			code = "CE-" + DateTimeUtil.NowMS() + "-" + RandomUtil.Word(6);
		}
		break;
		default:
			code = "SN." + DateTimeUtil.Now("yyyyMMddHHmmssSSS") + "." + RandomUtil.Word(6);
			break;
		}
		
		return code;
	}

}
