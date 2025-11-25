package michael.spica.mybatisx.service;

import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.service.BaseService;
import michael.spica.mybatisx.controller.request.product.ProductCreateRequest;
import michael.spica.mybatisx.entity.Product;

/**
 * Created by michael on 2025-11-25.
 */
public interface ProductService extends BaseService<Product, Long> {

    R<Product> create(ProductCreateRequest request);
}
