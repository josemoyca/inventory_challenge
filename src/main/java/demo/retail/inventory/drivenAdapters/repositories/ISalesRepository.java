package demo.retail.inventory.drivenAdapters.repositories;

import demo.retail.inventory.models.Sales;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesRepository extends ReactiveMongoRepository<Sales, String> {
}
