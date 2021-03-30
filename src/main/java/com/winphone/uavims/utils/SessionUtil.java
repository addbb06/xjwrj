package com.winphone.xjwrj.utils;

import com.winphone.xjwrj.auth.model.TokenModel;
import com.winphone.xjwrj.auth.util.JwtUtil;
import com.winphone.xjwrj.entity.Flyer;
import com.winphone.xjwrj.entity.Organization;
import com.winphone.xjwrj.entity.SysUser;
import com.winphone.xjwrj.exception.TokenValidationException;
import com.winphone.xjwrj.service.sys.IOrganizationService;
import com.winphone.xjwrj.service.sys.ISysUserService;
import com.winphone.xjwrj.service.device.IFlyerService;
import com.winphone.xjwrj.utils.base.HttpStatus;
import com.winphone.xjwrj.utils.webutil.Global;
import org.apache.shiro.SecurityUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2017/12/14
 * @Modified By:
 */
public class SessionUtil {

    private static ISysUserService sysUserService = SpringContextHolder.getBean("sysUserServiceImpl");

    private static IFlyerService flyerService = SpringContextHolder.getBean("flyerServiceImpl");

    private static volatile SessionUtil instance = null;

    private static IOrganizationService iOrganizationService = SpringContextHolder.getBean("organizationServiceImpl");


    private SessionUtil() {
    }

    public static SessionUtil getInstance() {
        if (instance == null) {
            synchronized (SessionUtil.class) {
                if (instance == null) {
                    instance = new SessionUtil();
                }
            }
        }
        return instance;
    }

    public static SysUser getCurrentUser() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        return user;
    }

    /**
     * 获取当前 系统用户 实体
     *
     * @return
     */
    public static SysUser getCurrentUserByToken() {
        SysUser sysUser = null;
        String token = Global.getHttpServletRequest().getHeader(JwtUtil.HEADER_STRING);
        TokenModel model = JwtUtil.validateToken(token);

        try {
            sysUser = sysUserService.selectById(model.getUserId());
            // 根据id获取去该orgid下的所有组织机构
            List<Organization> organizationList = iOrganizationService.selectByPid(sysUser.getOrgId());
            List<Long> orgIds = new ArrayList<Long>();
            for (Organization org : organizationList){
                orgIds.add(org.getId());
            }
            sysUser.setOrgIds(orgIds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenValidationException(HttpStatus.ERROR, "查询user失败");
        }
        return sysUser;
    }

    /**
     * 获取当前 飞手 实体
     */
    public static Flyer getCurrentFlierByToken() {
        Flyer flyer = null;
        String token = Global.getHttpServletRequest().getHeader(JwtUtil.HEADER_STRING);
        TokenModel model = JwtUtil.validateToken(token);

        try {
            flyer= flyerService.selectById(model.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenValidationException(HttpStatus.ERROR, "查询flyer失败");
        }
        return flyer;
    }

}
