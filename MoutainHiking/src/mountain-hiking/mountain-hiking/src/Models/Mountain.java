/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;

public class Mountain implements Serializable{
    private int code;
    private String moutain;
    private String province;
    private String description;

    public Mountain() {
    }

    public Mountain(int code, String moutain, String province, String description) {
        this.code = code;
        this.moutain = moutain;
        this.province = province;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMoutain() {
        return moutain;
    }

    public void setMoutain(String moutain) {
        this.moutain = moutain;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
