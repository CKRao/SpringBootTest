package com.clarkrao.springboot.excel;

public class ExcelModel {
    private String number;
    private String cpuId;
    private String coosId;
    private String coosKey;
    private String ORCodeImgUrl;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCpuId() {
        return cpuId;
    }

    public void setCpuId(String cpuId) {
        this.cpuId = cpuId;
    }

    public String getCoosId() {
        return coosId;
    }

    public void setCoosId(String coosId) {
        this.coosId = coosId;
    }

    public String getCoosKey() {
        return coosKey;
    }

    public void setCoosKey(String coosKey) {
        this.coosKey = coosKey;
    }

    public String getORCodeImgUrl() {
        return ORCodeImgUrl;
    }

    public void setORCodeImgUrl(String ORCodeImgUrl) {
        this.ORCodeImgUrl = ORCodeImgUrl;
    }
}
