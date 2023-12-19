package demo.retail.inventory.drivenAdapters.repositories;

import demo.retail.inventory.models.Inventory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryRepository extends ReactiveMongoRepository<Inventory, String> {
}
