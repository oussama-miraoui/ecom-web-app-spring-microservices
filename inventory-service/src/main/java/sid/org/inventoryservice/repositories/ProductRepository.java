package sid.org.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sid.org.inventoryservice.entities.Product;

@RepositoryRestResource
@CrossOrigin("*")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
