package com.ecommerce.app.repository;

import com.ecommerce.app.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //@Query("SELECT d FROM tb_product d JOIN FETCH d.municipio m JOIN FETCH m.estado")
    //List<Distrito> findDistritosComMunicipioEEstado();
}
