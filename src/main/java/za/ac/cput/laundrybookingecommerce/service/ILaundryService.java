/**
 * ILaundryService.java
 * Author: Snalo (230541844)
 * Date: 25 July 2026
 */
package za.ac.cput.laundrybookingecommerce.service;

import za.ac.cput.laundrybookingecommerce.domain.LaundryService;
import java.util.List;

public interface ILaundryService extends IService<LaundryService, String> {
    List<LaundryService> getServicesByName(String name);
}

