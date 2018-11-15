package com.hema.newretail.backstage.common.mongodb;
import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigDecimal;
/**
 * @Department 新零售
 * @ClassName w
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/11/9 11:43
 * @Version 1.0
 **/

@ReadingConverter
@WritingConverter
public class BigDecimalToDecimal128Converter implements Converter<BigDecimal, Decimal128> {

    @Override
    public Decimal128 convert(BigDecimal bigDecimal) {
        return new Decimal128(bigDecimal);
    }

}
