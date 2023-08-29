package net.muhammadsaad.rest.service;


import java.util.List;
import java.util.Map;

public interface DashboardService {

    long getProductsCount(Boolean active);
    long getCategoriesCount(Boolean active);
    long getBrandsCount(Boolean active);

    List<Map<String, Object>> getNumberOfProductsByCategory();
    List<Map<String, Object>> getNumberOfProductsByBrand();


}
