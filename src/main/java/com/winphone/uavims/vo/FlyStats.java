package com.winphone.xjwrj.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author LiaoJunJie
 * @version V1.0
 * @package com.winphone.xjwrj.vo
 * @description 飞行统计信息vo
 * @date 2018-04-23 18:16
 */
public class FlyStats implements Serializable {
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 飞手ID
     */
    private Long flierId;
    /**
     * 飞手名称
     */
    private String flierName;

    /**
     * 飞行次数
     */
    private Integer flyTimes;
    /**
     * 里程数
     */
    private Double mileage;
    /**
     * 飞行时长(分)
     */
    private Double flyTime;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getFlierId() {
        return flierId;
    }

    public void setFlierId(Long flierId) {
        this.flierId = flierId;
    }

    public String getFlierName() {
        return flierName;
    }

    public void setFlierName(String flierName) {
        this.flierName = flierName;
    }

    public Integer getFlyTimes() {
        return flyTimes;
    }

    public void setFlyTimes(Integer flyTimes) {
        this.flyTimes = flyTimes;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getFlyTime() {
        return flyTime;
    }

    public void setFlyTime(Double flyTime) {
        this.flyTime = flyTime;
    }
}
