package com.megazone.springbootboilerplate.shop.infra.mapper;

import com.megazone.springbootboilerplate.shop.domain.tier.ShopTierType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Component
public class EnumTypeHandler extends BaseTypeHandler<ShopTierType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ShopTierType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ShopTierType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return Arrays.stream(ShopTierType.values())
            .filter(it -> it.getCode().equals(code))
            .findAny()
            .orElse(ShopTierType.BRONZE);
    }

    @Override
    public ShopTierType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public ShopTierType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
