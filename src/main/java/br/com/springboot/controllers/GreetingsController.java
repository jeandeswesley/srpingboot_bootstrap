package br.com.springboot.controllers;

import br.com.springboot.curso.treinamento.model.Usuario;
import br.com.springboot.curso.treinamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingsController {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @DeleteMapping(value = "delete")/*Mapeia a URL*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<String> delete (@RequestParam Long iduser){ /*Recebe os dados para deletar*/

       usuarioRepository.deleteById(iduser);

        return new ResponseEntity<String>("Usuário deletado com sucesso!", HttpStatus.OK);/*Retorna mensagem deletada*/
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

}
