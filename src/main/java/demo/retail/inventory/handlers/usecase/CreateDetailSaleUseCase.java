package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.repositories.IProductRepository;
import demo.retail.inventory.drivenAdapters.repositories.ISalesRepository;
import demo.retail.inventory.handlers.exception.ResourceNotFoundException;
import demo.retail.inventory.models.mapper.SalesMapper;
import demo.retail.inventory.models.DTO.SalesDTO;
import demo.retail.inventory.models.Product;
import demo.retail.inventory.models.SalesTypes;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateDetailSaleUseCase {
    private ISalesRepository salesRepository;
    private IProductRepository productRepository;

    public CreateDetailSaleUseCase(ISalesRepository salesRepository, IProductRepository productRepository) {
        this.salesRepository = salesRepository;
        this.productRepository = productRepository;
    }

    public Mono<SalesDTO> apply(SalesDTO salesDTO) {
        return productRepository.findById(salesDTO.getProductId())
                .flatMap(product -> {
                    if (product == null) {
                        String message = ResourceNotFoundException.buildExceptionMessage(Product.class, "productId", salesDTO.getProductId());
                        return Mono.error(new ResourceNotFoundException(message));
                    }
                    salesDTO.setType(SalesTypes.DETAIL.toString());
                    salesDTO.setDiscountApplied(product.getRetailPercentage());
                    return salesRepository.save(SalesMapper.getSales(salesDTO));
                })
                .map(SalesMapper::getSalesDTO);
    }
}
