package com.hema.newretail.backstage.common.utils.rongyun;
import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigDecimal;

/**
 * @Department 新零售
 * @ClassName BigdecimalToDecimal128
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/11/12 10:22
 * @Version 1.0
 **/
@ReadingConverter
@WritingConverter
public class Decimal128ToBigdecimal implements Converter<Decimal128, BigDecimal>  {
    @Override
    public BigDecimal convert(Decimal128 decimal128) {
        return decimal128.bigDecimalValue();
    }
}




