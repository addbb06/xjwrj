package com.winphone.xjwrj.auth.model;

import java.io.Serializable;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2017/12/05
 * @Modified By:
 */

public class TokenModel implements Serializable {

    private Integer userId;

    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
