package com.winphone.xjwrj.auth.util;

import com.winphone.xjwrj.auth.model.TokenModel;
import com.winphone.xjwrj.exception.TokenValidationException;
import com.winphone.xjwrj.utils.base.HttpStatus;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2017/12/05
 * @Modified By:
 */

public class JwtUtil {
    // 0.5 hour 1800_000
    // 30天 3600 *24*30 = 2592000_000
    // 1天  3600 *24 = 86400_000
    static final long EXPIRATION_TIME = 86400_000;
    static final String SECRET = "uav-Secret";
    public static final String TOKEN_PREFIX = "uav";
    public static final String HEADER_STRING = "ACCESS-TOKEN";

    public static final String TOKENMODEL_NAME = "ACCESS-TOKEN";


    public static String generateToken(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map

        TokenModel tokenModel  = new TokenModel();
        tokenModel.setUserId(userId);
        tokenModel.setTokenId(UUID.randomUUID().toString());
        map.put(TOKENMODEL_NAME, tokenModel);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    /**
     *  校验token
     * @param token
     * @return
     */
    public static TokenModel validateToken(String token) {
        if (token != null) {
            // parse the token.
            Map<String,Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX+" ", ""))
                    .getBody();
            TokenModel tokenModel = null;
            try {
                tokenModel = (TokenModel)mapToObject((Map<String,Object>)body.get(TOKENMODEL_NAME),TokenModel.class);
            }catch (Exception e){
                throw new TokenValidationException(HttpStatus.ERROR,"转型失败");
            }
//            TokenModel tokenModel = (TokenModel) (body.get(TOKENMODEL_NAME));
            if(tokenModel == null){
                throw new TokenValidationException(HttpStatus.ERROR,"Wrong token without tokenModel");
            }else{
                return tokenModel;
            }
        }else{
            throw new TokenValidationException(HttpStatus.ERROR,"Missing token");
        }
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (map == null){
            return null;
        }
        Object obj = beanClass.newInstance();

        org.apache.commons.beanutils.BeanUtils.populate(obj, map);

        return obj;
    }

    /**
     *  获取当前 用户 实体
     * @param request
     * @return
     */
//    public TbSysUser getCurrentUser(HttpServletRequest request){
////        if (!StringUtils.equals(GlobaConfig.access_token,"true")){
////            throw new TokenValidationException("请检查yml配置");
////        }
//        String token = request.getHeader(JwtUtil.HEADER_STRING);
//        TokenModel model = JwtUtil.validateToken(token);
//        TbSysUser tbSysUser;
//        try{
//            tbSysUser =  sys.selectById(model.getUserId());
//        }catch (Exception e){
//            throw new TokenValidationException("查询user 失败");
//        }
//        return tbSysUser;
//    }
}
