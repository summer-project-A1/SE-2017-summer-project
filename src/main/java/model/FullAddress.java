package model;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

public class FullAddress {
    private String fullAddressID;      // 保存在mongodb中的完整address
    private String province;
    private String city;
    private String district;
    private String address;
    private Boolean isDefault;         // 是否为默认收货地址
    
    private String fullAddressString;   // 四部分地址拼起来的字符串，只用于前端展示
    
    /* ======================================================= */
    
    public FullAddress() {
        
    }
    
    public FullAddress(Map fullAddressMap) {
        this.fullAddressID = ((ObjectId)fullAddressMap.get("_id")).toString();
        this.province = (String)fullAddressMap.get("province");
        this.city = (String)fullAddressMap.get("city");
        this.district = (String)fullAddressMap.get("district");
        this.address = (String)fullAddressMap.get("address");
        this.isDefault = (Boolean)fullAddressMap.get("isDefault");
    }
    
    public Map toMap() {
        Map fullAddressMap = new HashMap();
        fullAddressMap.put("fullAddressID", this.fullAddressID==null? null:new ObjectId(this.fullAddressID));
        fullAddressMap.put("province", this.province);
        fullAddressMap.put("city", this.city);
        fullAddressMap.put("district", this.district);
        fullAddressMap.put("address", this.address);
        fullAddressMap.put("isDefault", this.isDefault);
        return fullAddressMap;
    }
    
    public String toFullAddressString() {
        return this.province + this.city + this.district + this.address;
    }
    
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
    public String getFullAddressString() {
        return fullAddressString;
    }
    public void setFullAddressString(String fullAddressString) {
        this.fullAddressString = fullAddressString;
    }
    
}