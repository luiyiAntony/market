package com.luiyi.market.persistence;

import com.luiyi.market.persistence.crud.ProductoCrudRepository;
import com.luiyi.market.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
