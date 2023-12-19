package demo.retail.inventory.drivenAdapters.repositories;

import demo.retail.inventory.models.Inventory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface IInventoryRepository extends ReactiveMongoRepository<Inventory, String> {
    Flux<Inventory> findAllBy(Pageable pageable);
}
