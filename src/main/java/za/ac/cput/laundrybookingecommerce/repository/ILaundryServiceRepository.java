/**
 * ILaundryServiceRepository.java
 * Author: Snalo (230541844)
 * Date: 25 July 2026
 */
package za.ac.cput.laundrybookingecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.laundrybookingecommerce.domain.LaundryService;

/**
 * Spring Data JPA Repository interface for LaundryService entity persistence operations.
 */
@Repository
public interface ILaundryServiceRepository extends JpaRepository<LaundryService, String> {
}
