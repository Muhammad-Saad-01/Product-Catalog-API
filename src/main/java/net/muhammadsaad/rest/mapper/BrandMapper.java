package net.muhammadsaad.rest.mapper;

import net.muhammadsaad.rest.entity.Brand;
import net.muhammadsaad.rest.model.BrandModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BrandMapper {

    BrandModel toModel(Brand brandModel);

    Brand toEntity(BrandModel brandModel);

    List<BrandModel> toModels(List<Brand> brandList);

    void updateBrandFromModel(BrandModel brandModel, @MappingTarget Brand brand);
    @Mapping(target = "id", ignore = true)
    Brand updateBrand(BrandModel brandModel, @MappingTarget Brand brand);
}
