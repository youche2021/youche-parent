package com.youche.hbase.mapper;

import com.youche.hbase.model.Product;
import com.youche.hbase.template.RowMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class ProductRowMapper implements RowMapper<Product> {

    private static byte[] COLUMN_FAMILY = "p".getBytes();
    private static byte[] USER = "user".getBytes();
    private static byte[] NAME = "name".getBytes();
    private static byte[] CCC = "ccc".getBytes();
    private static byte[] WIDTH = "width".getBytes();
    private static byte[] HEIGHT = "height".getBytes();

    @Override
    public Product mapRow(Result result, int rowNum) throws Exception {
        Product product = new Product();

        byte[] userByte = result.getValue(COLUMN_FAMILY, USER);
        if (userByte != null) {
            String user = Bytes.toString(userByte);
            product.setUser(user);
        }

        byte[] nameByte = result.getValue(COLUMN_FAMILY, NAME);
        if (nameByte != null) {
            String name = Bytes.toString(nameByte);
            product.setName(name);
        }

        byte[] cccByte = result.getValue(COLUMN_FAMILY, CCC);
        if (cccByte != null) {
            String cccString = Bytes.toString(cccByte);
            double ccc = Double.valueOf(cccString).doubleValue(); // 因为存储时用了string，所以查询时也是string
            product.setCcc(ccc);
        }

        byte[] widthByte = result.getValue(COLUMN_FAMILY, WIDTH);
        if (widthByte != null) {
            String widthString = Bytes.toString(widthByte);
            double width = Double.valueOf(widthString).doubleValue();
            product.setWidth(width);
        }

        byte[] heightByte = result.getValue(COLUMN_FAMILY, HEIGHT);
        if (heightByte != null) {
            String heightString = Bytes.toString(heightByte);
            float height = Float.valueOf(heightString).floatValue();
            product.setHeight(height);
        }

        return product;
    }
}
