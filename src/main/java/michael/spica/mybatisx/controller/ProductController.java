package michael.spica.mybatisx.controller;

import jakarta.annotation.Resource;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.BaseController;
import michael.spica.mybatisx.controller.request.product.ProductCreateRequest;
import michael.spica.mybatisx.entity.Product;
import michael.spica.mybatisx.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by michael on 2025-11-25.
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;

    @PostMapping
    public R<Product> create(@RequestBody ProductCreateRequest request) {
        return productService.create(request);
    }
}
