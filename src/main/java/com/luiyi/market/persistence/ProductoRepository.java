package com.luiyi.market.persistence;

import com.luiyi.market.domain.Product;
import com.luiyi.market.domain.repository.ProductRepository;
import com.luiyi.market.persistence.crud.ProductoCrudRepository;
import com.luiyi.market.persistence.entity.Producto;
import com.luiyi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // le indicamos a Spring que esta clase se encarga de interactuar con la base de datos
//@Component // Es una generalizacion (Repository es un tipo de Component), mejor ser preciso y isar Repository
public class ProductoRepository implements ProductRepository {
    @Autowired // to use @Autowired is obligatory that the attribute (ProductoCrudRepository in this case) is a spring component
    private ProductoCrudRepository productoCrudRepository;
    @Autowired // in this case the ProductMapper interface is not a spring component (@Mapper is not a spring component), but the objects that implement this interface are spring components
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods)); // mapper.toProducts only can receive List<Producto> but we have an Optional<List<Producto>> so we first use MAP FUNCTION that can use List<Producto> and return an Optional, very convenient :D !!!.
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        Optional<Producto> producto = productoCrudRepository.findById(productId);
        return producto.map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        productoCrudRepository.save(mapper.toProducto(product));
        return null;
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
