package com.luiyi.market.persistence;

import com.luiyi.market.persistence.crud.ProductoCrudRepository;
import com.luiyi.market.persistence.entity.Producto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // le indicamos a Spring que esta clase se encarga de interactuar con la base de datos
//@Component // Es una generalizacion (Repository es un tipo de Component), mejor ser preciso y isar Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(Integer idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProducto(Integer idProducto) {
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    public void delete(Integer idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }
}
