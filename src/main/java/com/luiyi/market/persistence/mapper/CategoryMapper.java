package com.luiyi.market.persistence.mapper;

import com.luiyi.market.domain.Category;
import com.luiyi.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration // le indicamos que haga la inversa de Mappings
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
