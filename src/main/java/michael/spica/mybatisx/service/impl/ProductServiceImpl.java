package michael.spica.mybatisx.service.impl;

import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.service.impl.BaseServiceImpl;
import michael.spica.mybatisx.controller.request.product.ProductCreateRequest;
import michael.spica.mybatisx.entity.Product;
import michael.spica.mybatisx.mapper.ProductMapper;
import michael.spica.mybatisx.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by michael on 2025-11-25.
 */
@Slf4j
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product, Long> implements ProductService {

    @Override
    public R<Product> create(ProductCreateRequest request) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        product.setTenantId(1L);
        int i = this.getBaseMapper().insert(product);
        if (i > 0) {
            log.info("创建产品成功：{}", product);
            return R.ok(product);
        }
        return R.fail("创建产品失败");
    }
}
