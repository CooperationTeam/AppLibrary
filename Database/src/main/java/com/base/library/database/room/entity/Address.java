package com.base.library.database.room.entity;

/**
 * @author reber
 */
public class Address {

    private String province;
    private String provinceId;

    private String city;
    private String cityId;

    public String getProvince() {
        return province;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public String getCity() {
        return city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
