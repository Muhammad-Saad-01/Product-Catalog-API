package net.muhammadsaad.rest.service.impl;

import lombok.AllArgsConstructor;
import net.muhammadsaad.rest.repository.BrandRepository;
import net.muhammadsaad.rest.repository.CategoryRepository;
import net.muhammadsaad.rest.repository.ProductRepository;
import net.muhammadsaad.rest.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Override
    public long getProductsCount(Boolean active) {
        if (active == null)
            return productRepository.count();
        return productRepository.countAllByActiveEquals(active);
    }


    @Override
    public long getCategoriesCount(Boolean active) {
        if (active == null)
            return categoryRepository.count();
        return categoryRepository.countAllByActiveEquals(active);
    }

    @Override
    public long getBrandsCount(Boolean active) {
        if (active == null)
            return brandRepository.count();
        return brandRepository.countAllByActiveEquals(active);
    }

    @Override
    public List<Map<String, Object>> getNumberOfProductsByCategory() {
        List<Object[]> result = productRepository.countProductsByCategory();
        return getMaps(result);
    }

    private List<Map<String, Object>> getMaps(List<Object[]> result) {
        List<Map<String, Object>> list =  new ArrayList<>();
        for (Object[] object : result) {
        Map<String, Object> map = new HashMap<>();
            map.put("name", object[0]);
            map.put("count", object[1]);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getNumberOfProductsByBrand() {
        List<Object[]> result = productRepository.countProductsByBrand();
        return getMaps(result);
    }
}
