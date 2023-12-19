package demo.retail.inventory.drivenAdapters.repositories;

import demo.retail.inventory.models.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
}
