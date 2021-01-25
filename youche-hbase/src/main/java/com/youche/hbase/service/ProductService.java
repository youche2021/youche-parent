package com.youche.hbase.service;

import com.youche.hbase.mapper.ProductRowMapper;
import com.youche.hbase.model.Product;
import com.youche.hbase.template.HbaseTemplate;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private HbaseTemplate hbaseTemplate;

    @Autowired
    public void setHbaseTemplate(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    public List<Product> query(String startRow, String endRow) {
        Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(endRow));
        scan.setCaching(5000);
        return this.hbaseTemplate.find("t_product",scan, new ProductRowMapper());
    }

    public Product query(String rowName) {
        return this.hbaseTemplate.get("t_product", rowName, new ProductRowMapper());
    }

    public void saveOrUpdate(Product product) {
    }
}
