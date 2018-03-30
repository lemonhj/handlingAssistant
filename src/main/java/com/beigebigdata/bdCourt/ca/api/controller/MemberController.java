package com.beigebigdata.bdCourt.ca.api.controller;



import com.beigebigdata.bdCourt.ca.api.constants.CAConstants;
import com.beigebigdata.bdCourt.ca.api.entity.Member;
import com.beigebigdata.bdCourt.ca.api.service.MemberService;
import com.beigebigdata.bdCourt.ca.api.view.MemberView;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.beigebigdata.bdCourt.ca.common.entity.FieldValue;
import com.beigebigdata.bdCourt.ca.common.service.FieldService;
import com.beigebigdata.bdCourt.ca.common.service.MakerCodeService;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.core.annotation.Debug;
import com.septinary.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: MemberController
 * @Description:会员操作类
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private MakerCodeService makerCodeService;

    @Autowired
    private CaseBaseService caseBaseService;

    /**
     * 注册
     * @param member
     * @return
     */
    @RequestMapping(value = "/member_register",method = RequestMethod.POST)
    @ResponseBody
    @Debug
    @SysTraceLog(description = "会员注册")
    public ViewJSON<Object> register(Member member){
        VerifyUtils.verifyReviseInfo(member,member.getMemUsername(),member.getMemPassword(),member.getMemType());

        String uname = member.getMemUsername();
        String pwd = member.getMemPassword().trim();
        String tel = member.getMemTel();
        boolean isStartWithNum = VerifyUtils.isNumber(uname.substring(0,1));
        if (isStartWithNum) return new ViewJSON<>("400","INVALID REQUEST","用户名不能以数字开头");
        boolean unameMatche = uname.matches("[0-9A-Za-z_]*");

        if (!unameMatche) return new ViewJSON<>("400","INVALID REQUEST","用户名只能包含字母、数字、下划线");
        if (pwd.length()<6 || pwd.length()>32) return new ViewJSON<>("400","INVALID REQUEST","密码长度要求：6-32位");
        if (tel != null && !VerifyUtils.isMobileNO(tel)) return new ViewJSON<>("400","INVALID REQUEST","手机号码格式有误");

        //判断是够已经注册
        int resultNum = memberService.isTelRegisted(member);
        if (resultNum == 1) return new ViewJSON<>("400","INVALID REQUEST","用户名已注册");
        if (resultNum == 2) return new ViewJSON<>("400","INVALID REQUEST","手机号码已注册");

        member = memberService.insertSelective(constructMember(member));

        if (member == null) return new ViewJSON<>("500","SERVER ERROR","用户保存异常");
        return new ViewJSON<>("200","success");

    }

    /**
     * @api {get} /v1.0/members/:memCode/collectionNews 获取指定用户收藏的资讯
     * @apiVersion 0.1.0
     * @apiName get collect informations
     * @apiGroup Members
     *
     * @apiParam {String} memCode 用户Code.
     *
     * @apiSuccess {String} code 返回状态码.
     * @apiSuccess {String} info 返回信息.
     * @apiSuccess {String} memo 返回提示.
     * @apiSuccess {String} data 返回数据.
     * @apiSuccess {String} hint 状态描述.
     * @apiSuccess {String} id 资讯ID.
     * @apiSuccess {String} title 资讯标题.
     * @apiSuccess {String} thumPic 资讯缩略图.
     * @apiSuccess {String} updateTime 资讯更新时间.
     * @apiSuccess {String} type 资讯类型.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "200",
     *      "info": "OK",
     *      "memo": "服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）",
     *      "data":[
     *         {
     *          "id": 10,
     *          "title": "周强：为建设平安中国法治中国作出积极贡献",
     *          "thumPic": "/images/news/yw5-0.jpg",
     *          "updateTime": "2017-06-23 15:12:14",
     *          "type": 2
     *         }],
     *         "hint": "success"
     *      }
     */
    @RequestMapping(value = "/{memCode}/collectionNews",method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<NewsInfo>> collectionNews(@PathVariable(value = "memCode")String memCode){
        VerifyUtils.verifyReviseInfo(memCode);
        List<NewsInfo> news = memberService.fetchCollectionNews(memCode);
        return new ViewJSON<>("200","OK",news);
    }


    /**
     * 登陆
     * @param
     * @return
     */
    @RequestMapping(value = "/member_login",method = RequestMethod.POST)
    @ResponseBody
    @Debug
    @SysTraceLog(description = "用户登录")
    public ViewJSON<Map<String,Object>> login(String username,String password){
        VerifyUtils.verifyReviseInfo(username, password);

        Member member = new Member();
        member.setMemUsername(username);
        member = memberService.selectOne(member);
        if (member == null) return new ViewJSON<>("404","USER NOT FOUND","用户不存在！");
        if(!member.getMemPassword().equals(memberService.password(password,member.getMemSecretkey())))
            return new ViewJSON<>("400","INVALID REQUEST","用户(tel:"+username+")密码错误。");
        if (member.getMemState() != 2) return new ViewJSON<>("401","Forbidden","用户(tel:"+username+")账户信息异常，请联系客服。");
        try{
            Map<String, Object> result = constructLoginResult(member);

            if (result.isEmpty()) return new ViewJSON<>("500","SERVER ERROR");
            return new ViewJSON<>("200","success",result);
        }catch (Exception e){
            e.printStackTrace();
            return new ViewJSON<>("500","SERVER ERROR");
        }
    }

    /**
     * 获取可注册角色
     * @return 角色集合
     */
    @RequestMapping(value = "/registerRoles",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取可注册角色")
    public ViewJSON<List<Map<String,String>>> registerRole(){

        List<FieldValue> fieldValues = fieldService.fetchFieldValue("MemberType");
        List<Map<String,String>> roles = new ArrayList<>();
        for (Iterator<FieldValue> iterator= fieldValues.iterator();iterator.hasNext();){
            FieldValue fieldValue = iterator.next();
            if (!fieldValue.getValue().equals("0")){
                Map<String,String> role = new HashMap<>();
                role.put("type",fieldValue.getValue());
                role.put("name",fieldValue.getParse());
               // role.put(fieldValue.getValue(),fieldValue.getParse());
                roles.add(role);
            }
        }
        return new ViewJSON<>("200","OK",roles);
    }

    /**
     * 获取指定用户的案件列表
     * @param memCode 用户code
     * @return 案件列表
     */
    @RequestMapping(value = "/{memCode}/cases",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取指定用户的案件列表")
    public ViewJSON<List<HashMap<String,Object>>> fetchCaseBases(@PathVariable(value = "memCode")String memCode){
        VerifyUtils.verifyReviseInfo(memCode);

        CaseBase caseBase = new CaseBase();
        caseBase.setCaseCreater(memCode);
        caseBase.setCaseStatus(0);
        List<HashMap<String,Object>> caseBases = caseBaseService.fetchMemCases(memCode,0);

        return new ViewJSON<>("200","OK",caseBases);
    }

    /**
     * 获取指定用户办理的历史案件(包括终止和完成的)
     * @param memCode 用户code
     * @return 案件列表
     */
    @RequestMapping(value = "/{memCode}/historyCase",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取指定用户办理的历史案件(包括终止和完成的)")
    public ViewJSON<List<HashMap<String,Object>>> fetchHistoryCaseBases(@PathVariable(value = "memCode")String memCode){
        VerifyUtils.verifyReviseInfo(memCode);

        List<HashMap<String,Object>> caseBases = caseBaseService.fetchHistoryCaseBases(memCode);
        return new ViewJSON<>("200","OK",caseBases);
    }



    /**
     * 构建会员
     * @param member
     */
    private Member constructMember(Member member) {
        member.setMemSecretkey(RandomUtil.Word(6));
        member.setMemCode(makerCodeService.makeCode("MemberCode", null));
        member.setMemPassword(memberService.password(member.getMemPassword(), member.getMemSecretkey()));
        member.setMemState(2); //1:未授权
        member.setMemCreate(new Date());
        member.setMemUpdate(new Date());
        member.setMemDeleted(false);
        return member;
    }


    /**
     * 构建登录返回数据
     * @param member
     * @return
     * @throws Exception
     */
    private Map<String, Object> constructLoginResult(Member member) throws Exception {
        GUIDUtil myGUID = new GUIDUtil();
        String token = AESUtil.aesEncrypt(myGUID.toString(), CAConstants.AESKEY);

        FieldValue fieldValue = new FieldValue();
        fieldValue.setField(12021L);
        fieldValue.setValue(member.getMemType()+"");
        fieldValue = fieldService.fetchFieldValue(fieldValue);

        Map<String,Object> result = new HashMap<>();
        result.put("memCode",member.getMemCode());
        result.put("memUsername", member.getMemUsername());
        result.put("memType", fieldValue.getParse());
        result.put("memNum", member.getMemType());
        String tel = member.getMemTel();
        if (tel != null) result.put("memTel", tel);
        result.put("token",token);

        return result;
    }
    
    /***********************个人中心相关接口(lihuayang) START***************************/
	/**
	 * 获取个人中心中的数据，通过登录过的已经存在的
	 * @param code 用户唯一标志,表中不能重复
	 * @param req
	 * @param res
	 * @return
	 * 注解：暂时从前端传用户参数：code,username,以后从带的头域里面获取用户的code,username
	 */
	@ResponseBody
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ViewJSON<MemberView> getMemberInfoBySessionId(String code, HttpServletRequest req, HttpServletResponse res) {
		if(null == code || code == "") {
			return new ViewJSON<MemberView>("401","Unauthorized","用户账户未登陆，请登陆！");
		}
		MemberView view = this.memberService.getMemberViewInfo(code);
		if(null != view) {
			return new ViewJSON<MemberView>("200","ok",view);
		} else {
			return new ViewJSON<MemberView>("1201","查无目标数据","此用户不存在！");
		}
	}
	
	
	/**
	 * 修改会员信息
	 * @param type 类型：包括修改身份(identity),手机绑定(tel),密码(psd)
	 * @param value  类型type对应的值
 	 * @param psdValue 修改密码时的确认密码 ，要与value一致  
 	 * @param psdOriginal 修改密码时输入的旧密码，验证用户
	 * @param req
	 * @param rep
	 * @return json
	 * 注解：暂时从前端传用户参数：code,username,以后从带的头域里面获取用户的code,username
	 */
	@ResponseBody
	@RequestMapping(value="/upd", method =  RequestMethod.POST)
	public ViewJSON<Void> updateMemberInfoByWhere(String code, String type, String value, String psdValue, String psdOriginal,
			HttpServletRequest req, HttpServletResponse rep) {
		if(null == code || code == "") {
			return new ViewJSON<Void>("401","Unauthorized","用户账户未登陆，请登陆！");
		}
		if("psd".equals(type)) {
			if(!value.equals(psdValue)) {
				return new ViewJSON<Void>("403", "Forbidden", "两次密码输入不一致！");
			}
		} else if(null == type || "" == type) {
			return new ViewJSON<Void>("403","Forbidden", "传入的type类型不能为空！");
		} else if(null == value || "" == value) {
			return new ViewJSON<Void>("403","Forbidden", "传入的type对应的value不能为空！");
		}
		ViewJSON<Void> viewJson = this.memberService.updataMemberInfo(code, type, value, psdOriginal);
		return viewJson;
	}


	
	/**
	 * 根据手机号码匹配用户信息，返回用户的部分信息
	 * @param tel
	 * @return 返回用户的用户名及code
	 */
	@ResponseBody
	@RequestMapping(value="/check/{tel}",method = RequestMethod.GET)
	public ViewJSON<HashMap<String, Object>> findMemberInfoByTel(@PathVariable(value="tel")String tel){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memTel", tel);
		Member member = this.memberService.findMemberInfoByMap(map);
		if(null != member) {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("memUsername", member.getMemUsername());
			return new ViewJSON<HashMap<String, Object>>("200","ok",resultMap);
		} else {
			return new ViewJSON<HashMap<String, Object>>("404","NOT FOUND","该手机未绑定账户！");
		}
	}
	
	/**
	 * 密码找回
	 * @param tel 用户绑定的手机号码
	 * @param psd 新密码
	 * @param checkPsd 确认密码
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value="/back-psd",method = RequestMethod.POST)
	public ViewJSON<Void> backPasswordByTel(String tel, String psd, String checkPsd) {
		if(null == tel && "".equals(tel)) {
			return new ViewJSON<Void>("403","Forbidden", "用户电话号码不能为空！");
		} else if((null == psd && "".equals(psd)) || (null == checkPsd && "".equals(checkPsd))) {
			return new ViewJSON<Void>("403","Forbidden", "新密码不能为空！");
		} else if(!psd.equals(checkPsd)) {
			return new ViewJSON<Void>("403","Forbidden", "两次密码输入不一致！");
		} else {
			return this.memberService.updateMemberPsdByTel(tel, psd);
		}
	} 
    /***********************个人中心相关接口 END****************************************/
}
 