package com.hema.newretail.backstage.common.mongodb;

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.mongodb
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-24 11:49
 */
public class Decimal128ToBigDecimalConverter implements Converter<Decimal128, BigDecimal> {

    @Override
    public BigDecimal convert(Decimal128 decimal128) {
        return decimal128.bigDecimalValue();
    }



}
