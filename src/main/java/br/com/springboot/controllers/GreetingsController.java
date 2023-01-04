package br.com.springboot.controllers;

import br.com.springboot.curso.treinamento.model.Products;
import br.com.springboot.curso.treinamento.model.Usuario;
import br.com.springboot.curso.treinamento.repository.UsuarioRepository;
import br.com.springboot.curso.treinamento.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingsController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductsRepository productsRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/pessoa/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {

        return "Curso Spring Boot " + name + "!";
    }

    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuarioRepository.save(usuario); /*Grava no banco*/

        return "ola mundo " + nome;
    }
    @GetMapping(value = "listatodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*Retorna a lista em json*/
    }

    @PostMapping(value = "salvar")/*Mapeia a URL*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){ /*Recebe os dados para salvar*/

        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);/*Retorna a lista em json*/
    }

    @GetMapping(value = "buscaruserid")/*Mapeia a URL*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "iduser") Long iduser){ /*Recebe os dados para consultar*/

        Usuario usuario = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);/*Retorna o usuario em json*/
    }

    @GetMapping(value = "buscarPorNome")/*Mapeia a URL*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "name") String name){ /*Recebe os dados para consultar*/

        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);/*Retorna a busca do usuario em json*/
    }

    @PutMapping(value = "atualizar")/*Mapeia a URL*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){ /*Recebe os dados para atualizar*/
        if(usuario.getId() == null) {
            return new ResponseEntity<String>("ID não foi informado para atualização!", HttpStatus.OK);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);/*Retorna usuario em json*/
    }

    @DeleteMapping(value = "delete")/*Mapeia a URL*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<String> delete (@RequestParam Long iduser){ /*Recebe os dados para deletar*/

        usuarioRepository.deleteById(iduser);

        return new ResponseEntity<String>("Usuário deletado com sucesso!", HttpStatus.OK);/*Retorna mensagem deletada*/
    }


    @GetMapping(value = "listaprodutos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Products>> listaProduto(){

        List<Products> products = productsRepository.findAll();

        return new ResponseEntity<List<Products>>(products, HttpStatus.OK);/*Retorna a lista em json*/
    }

    @PostMapping(value = "salvarprodutos")/*Mapeia a URL*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<Products> salvarprodutos (@RequestBody Products products){ /*Recebe os dados para salvar*/

        Products product = productsRepository.save(products);

        return new ResponseEntity<Products>(product, HttpStatus.CREATED);/*Retorna a lista em json*/
    }
    @GetMapping(value = "buscarprodutoid/{id}")
    public ResponseEntity<Products> buscarprodutoid (@PathVariable("id") Long id){

        Products product = productsRepository.findById(id).get();

        return new ResponseEntity<Products>(product, HttpStatus.OK);
    }

    @GetMapping(value = "buscarProdutoPorNome")/*Mapeia a URL*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Products>> buscarProdutoPorNome (@RequestParam(name = "nameprod") String nameprod){ /*Recebe os dados para consultar*/

        List<Products> product = productsRepository.buscarProdutoPorNome(nameprod.trim().toUpperCase());

        return new ResponseEntity<List<Products>>(product, HttpStatus.OK);/*Retorna a busca do usuario em json*/
    }

    @PutMapping(value = "atualizaprod/{id}")
    @ResponseBody
    public ResponseEntity<?> atualizaprod (@RequestBody Products products){ /*Recebe os dados para atualizar*/
        if(products.getId() == null) {
            return new ResponseEntity<String>("ID não foi informado para atualização!", HttpStatus.OK);
        }

        Products product = productsRepository.saveAndFlush(products);

        return new ResponseEntity<Products>(products, HttpStatus.OK);/*Retorna produto em json*/
    }

    @DeleteMapping(value = "deleteprod/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteprod (@PathVariable("id") Long id) {

        productsRepository.deleteById(id);

        return new ResponseEntity<String>("Produto deletado com sucesso!", HttpStatus.OK);

    }
}
