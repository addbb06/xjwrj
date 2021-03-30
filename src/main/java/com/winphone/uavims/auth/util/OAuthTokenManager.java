package com.winphone.xjwrj.auth.util;


import com.alibaba.fastjson.JSON;
import com.winphone.xjwrj.auth.LogicInterface;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * OAuthTokenUtils
 * Token管理  测试类
 *
 * @author nizhigengvip@163.com
 * @version 2017-08-01
 */
public class OAuthTokenManager {
    private String APP_ID = "";
    private String APP_SECRET = "";
    private String KEY_SING = ""; //用於存放TOKEN的標誌,Redis
    private LinkedHashMap<String, Object> pairs = new LinkedHashMap();//封装json的map
    public static final int MINUTE_TTL = 60 * 1000;  //millisecond
    public static final int HOURS_TTL = 60 * 60 * 1000;  //millisecond
    public static final int DAY_TTL = 12 * 60 * 60 * 1000;  //millisecond


    private OAuthTokenManager() {
    }

    private static OAuthTokenManager single = null;

    public static OAuthTokenManager getInstance() {
        if (single == null) {
            single = new OAuthTokenManager();
        }
        return single;
    }

    public String getKEY_SING() {
        return KEY_SING;
    }

    public void setPairs(LinkedHashMap<String, Object> pairs) {
        this.pairs = pairs;
    }

    public LinkedHashMap<String, Object> getPairs() {
        return pairs;
    }

    public void put(String key, Object value) {//向json中添加属性，在js中访问，请调用data.map.key
        pairs.put(key, value);
    }

    public void remove(String key) {
        pairs.remove(key);
    }

    /**
     * 總體封裝
     *
     * @param appid
     * @param secret
     * @param logicInterface 回調函數
     * @return
     */
    public String token(String appid, String secret, LogicInterface logicInterface) {
        //获取appid和secret
        this.accessPairs(appid, secret);
        //验证appid和secretS，获取对象载体
        Object subject = this.loginAuthentication(logicInterface);
        //生成JWT签名数据ToKen
        String token = this.createToken(generalSubject(subject), MINUTE_TTL);
        return token;
    }

    public void accessPairs(String APP_ID, String APP_SECRET) {
        this.APP_ID = APP_ID;
        this.APP_SECRET = APP_SECRET;
        //this.KEY_SING = MD5Util.MD5Encode(APP_ID+"_"+APP_SECRET, "UTF-8").toUpperCase();//要用到的时候才用
    }

    public Object loginAuthentication(LogicInterface logicInterface) {
        if (StringUtils.isNotBlank(APP_ID) && StringUtils.isNotBlank(APP_SECRET)) {
            Map<String, Object> map = new HashMap<>();
            map.put("APP_ID", APP_ID);
            map.put("APP_SECRET", APP_SECRET);
            if (logicInterface == null || logicInterface.handler(map) == null) {
                return map;
            } else {
                return logicInterface.handler(map);
            }
        } else {
            return null;
        }
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public SecretKey generalKey() {
        String stringKey = APP_ID + APP_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 生成subject信息
     *
     * @param obj
     * @return
     */
    public static String generalSubject(Object obj) {
        if (obj != null) {
            return JSON.toJSONString(obj);
        } else {
            return "{}";
        }

    }

    /**
     * 创建token
     *
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public String createToken(String subject, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(APP_ID)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public Claims validateToken(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token).getBody();
        /*System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());*/
        return claims;
    }
}
