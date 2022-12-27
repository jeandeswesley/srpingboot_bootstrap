package br.com.springboot.curso.treinamento.repository;

import br.com.springboot.curso.treinamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%") //Faz o select desconsiderando maiusculas e minusculas
    List<Usuario> buscarPorNome(String name);
}
