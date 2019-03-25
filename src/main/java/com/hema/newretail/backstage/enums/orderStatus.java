package com.hema.newretail.backstage.enums;

public enum orderStatus {
    /*订单状态 0，未付款 1未制作，2，不能做，
            3，超时，4 待确认，6已处在制作队列中，
            7确认完毕放弃制作，8退款中，9退款成功，
            10已退券，11退款失败，129制作中，135未领取，
            143已领取，15制作异常*/

    ZERO("0","未付款"),
    ONE("1","未制作"),
    TWO("2","不能做"),
    THREE("3","超时"),
    FOUR("4","待确认"),
    SIX("6","已处在制作队列中"),
    SEVEN("7","确认完毕放弃制作"),
    EIGHT("8","退款中"),
    NINE("9","退款成功"),
    TEN("10","已退券"),
    ELEVEN("11","退款失败"),
    ONE_TWO_NINE("129","制作中"),
    ONE_THREE_FIVE("135","未领取"),
    ONE_FOUR_THREE("143","已领取"),
    FIFTEEN("15","制作异常");
    private String code;
    private String message;

    orderStatus(String code, String message) {
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
