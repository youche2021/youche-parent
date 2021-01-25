package com.youche.mongodb.controller;

import com.youche.mongodb.model.Product;
import com.youche.mongodb.repository.ProductRepository;
import com.youche.mongodb.utils.Pagination;
import com.youche.mongodb.utils.Result;
import com.youche.mongodb.utils.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Log4j2
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductController(ProductRepository productRepository, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
    }

    // 增
    @PostMapping("save")
    public ResponseEntity<Result<Product>> save(Product product) {
        if (!StringUtils.isEmpty(product.get_id())) {
            return ResultUtils.error();
        }
        productRepository.save(product);
        return ResultUtils.ok(product);
    }

    // 删 单个删除
    @GetMapping("deleteById")
    public ResponseEntity<Result<String>> deleteById(String id) {
        if (StringUtils.isEmpty(id)) {
            return ResultUtils.error();
        }

        productRepository.deleteById(id);
        return ResultUtils.ok();
    }

    // 删 批量删除
    @DeleteMapping("deleteByIds")
    public ResponseEntity<Result<String>> deleteByIds(@RequestBody String[] ids) {
        if (ids == null) {
            return ResultUtils.error();
        }
        List<String> idList = Arrays.asList(ids);

        Iterable<Product> deleteProducts = productRepository.findAllById((Iterable<String>) idList.iterator());
        productRepository.deleteAll(deleteProducts);

        return ResultUtils.ok();
    }

    // 改 单个
    @PostMapping("update")
    public ResponseEntity<Result<Product>> update(Product product) {
        if (StringUtils.isEmpty(product.get_id())) {
            return ResultUtils.error();
        }
        product = productRepository.save(product);
        return ResultUtils.ok(product, "更新成功");
    }

    // 查 查单个
    @GetMapping("findById")
    public ResponseEntity<Result<Product>> findById(String id) {
        Product product = productRepository.findById(id).get();
        return ResultUtils.ok(product);
    }

    // 查 查全部列表
    @GetMapping("findAll")
    public ResponseEntity<Result<List<Product>>> findAll() {
        List<Product> products = productRepository.findAll();
        return ResultUtils.ok(products);
    }

    // 查 分页查询
    @GetMapping("findPage")
    public ResponseEntity<Result<Pagination<Product>>> findPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.unsorted());
        Page<Product> productPage = productRepository.findAll(pageRequest);
        log.error(productPage.getClass());
        log.error(productPage.getPageable().getClass());
        return ResultUtils.ok(Pagination.from(productPage));
    }

    // 查 模糊查询
    @GetMapping("findByNameLike")
    public ResponseEntity<Result<List<Product>>> findByNameLike(String name) {
        List<Product> products = null;
        if (StringUtils.isEmpty(name)) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findByNameLike(name);
        }
        return ResultUtils.ok(products);
    }

    @GetMapping("findPageByNameMongoTemplate")
    public ResponseEntity<Result<List<Product>>> findPageByNameMongoTemplate(int page, int size, String name) {

        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name));
        List<Product> products = mongoTemplate.find(query, Product.class);

        return ResultUtils.ok(products);
    }

    // 查 根据条件过滤
    @GetMapping("findPageByNameLike")
    public ResponseEntity<Result<Pagination<Product>>> findPageByNameLike(int page, int size, String name) {
        if (StringUtils.isEmpty(name)) {
            return ResultUtils.error();
        }

        PageRequest pageRequest = PageRequest.of(page, size, Sort.unsorted());

        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name));
        List<Product> products = mongoTemplate.find(query, Product.class);

        long total = mongoTemplate.count(query, Product.class);

        return ResultUtils.ok(Pagination.build(products, total, pageRequest));
    }
}
