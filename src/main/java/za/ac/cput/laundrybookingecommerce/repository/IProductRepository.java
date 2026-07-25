/**
 * IProductRepository.java
 * Author: Snalo (230541844)
 * Date: 25 July 2026
 */
package za.ac.cput.laundrybookingecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.laundrybookingecommerce.domain.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {
}
