package br.com.springboot.curso.treinamento.repository;

import br.com.springboot.curso.treinamento.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query(value = "select p from Products p where upper(trim(p.name)) like %?1%") //Faz o select desconsiderando maiusculas e minusculas
    List<Products> buscarProdutoPorNome(String nameprod);


}
