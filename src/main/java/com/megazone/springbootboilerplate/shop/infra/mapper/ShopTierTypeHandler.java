package com.megazone.springbootboilerplate.shop.infra.mapper;

import com.megazone.springbootboilerplate.shop.domain.tier.Bronze;
import com.megazone.springbootboilerplate.shop.domain.tier.Gold;
import com.megazone.springbootboilerplate.shop.domain.tier.ShopTier;
import com.megazone.springbootboilerplate.shop.domain.tier.Silver;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

@Component
public class ShopTierTypeHandler extends BaseTypeHandler<ShopTier> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ShopTier parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ShopTier getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return switch (code) {
            case "Gold" -> new Gold();
            case "Silver" -> new Silver();
            case "Bronze" -> new Bronze();
            default -> new Bronze();
        };
    }

    @Override
    public ShopTier getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public ShopTier getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
