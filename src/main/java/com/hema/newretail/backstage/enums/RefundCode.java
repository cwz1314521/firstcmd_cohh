package com.hema.newretail.backstage.enums;

public enum RefundCode {
    /*1，冲击泵异常
    2，取杯口门异常
    3，自检异常
    4，加热设备异常
    5，制冷设备异常
    6，低水位传感器异常
    7，高水位传感器异常
    8，高低水位检测异常
    9，落杯异常
    10，断电存储异常
    11，托杯器上有杯
    12，落盖异常，卡盖
    13，长时间无人取杯
    14，环境异常
    15，无杯告警
    16，无盖告警
    17，arm机与单片机通信异常
    18，模组异常
    19，制作超时
    100，超时
    101，由于缺料放弃制作
    102，异常
    103，无法制作-缺料*/

    ONE("1","冲击泵异常"),
    TWO("2","取杯口门异常"),
    THREE("3","自检异常"),
    FOUR("4","加热设备异常"),
    FIVE("5","制冷设备异常"),
    SIX("6","低水位传感器异常"),
    SEVEN("7","高水位传感器异常"),
    EIGHT("8","高低水位检测异常"),
    NINE("9","落杯异常"),
    TEN("10","断电存储异常"),
    ELEVEN("11","托杯器上有杯"),
    TWELVE("12","落盖异常，卡盖"),
    THIRTH("13","长时间无人取杯"),
    FOURTH("14","环境异常"),
    FIFTEEN("15","无杯告警"),
    SIXTEEN("16","无盖告警"),
    SEVENTEEN("17","arm机与单片机通信异常"),
    EIGHTEEN("18","模组异常"),
    NINETEEN("19","制作超时"),
    ONE_HUNDRED("100","超时"),
    ONE_ZERO_ONE("101","由于缺料放弃制作"),
    ONE_ZERO_TWO("102","异常"),
    ONE_ZERO_THREE("103","无法制作-缺料");
    private String code;
    private String message;

    RefundCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
