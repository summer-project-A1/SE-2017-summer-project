package model;

public class FullAddress {
    private String fullAddressID;      // 保存在mongodb中的完整address
    private String province;
    private String city;
    private String district;
    private String address;
    private Boolean isDefault;         // 是否为默认收货地址
    
    /* ======================================================= */
    
    public String getFullAddressID() {
        return fullAddressID;
    }
    public void setFullAddressID(String fullAddressID) {
        this.fullAddressID = fullAddressID;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Boolean getIsDefault() {
        return isDefault;
    }
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
    
}