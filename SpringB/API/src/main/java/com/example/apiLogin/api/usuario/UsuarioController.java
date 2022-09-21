package com.example.apiLogin.api.usuario;

import com.example.apiLogin.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;



    //Retorna todos os usuários      //localhost:8088/usuario/getAll
    @GetMapping("/getAll")
    public List<Usuario> retornarUsuarios() {
        return usuarioRepository.findAll();
    }



    //Validação de login         //localhost:8088/usuario/login
    @PostMapping("/login")
    public String findByUsuarioAndSenha(@RequestBody UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioAndSenhaOptional = usuarioRepository.findByUsuarioAndSenha(usuarioDTO.usuario, usuarioDTO.senha);

        if (usuarioAndSenhaOptional.isEmpty()){
            return "";
        }else {
            return (UUID.randomUUID().toString());
        }
    }


    //Verifica se o registro existe no banco
    @GetMapping("/user")
    public String findByUsuario(@RequestBody FindByUsuarioDTO findByUsuarioDTO) {
        Optional<Usuario> findByUsuarioOptional = usuarioRepository.findByUsuario(findByUsuarioDTO.usuario);  //configurar a resposta

        if (findByUsuarioOptional.isEmpty()){
            return "";
        }else {
            return (findByUsuarioOptional.toString());
        }
    }


    //Cria um registro no banco
    @PostMapping("/add")
    public Usuario salvarUsuario(@RequestBody Usuario usuarioDaRequest) {
        return usuarioRepository.save(usuarioDaRequest);
    }



    //Passa latitude e longitude como parâmetro
    @PutMapping("/Update/")
    public ResponseEntity init( @RequestParam(value = "id", required = true, defaultValue = "id não informado") Long id,
                                @RequestParam(value = "latitude", required = true, defaultValue = "Latitude não informada") String latitude,
                                @RequestParam(value = "longitude", required = true, defaultValue = "Longitude não informada") String longitude ){
        System.out.println(latitude + longitude);
        return new ResponseEntity(longitude, HttpStatus.OK);
    }




    @PutMapping(path="/updateLocation/{id}")   //Passa latitude e longitude por JSON
    public @ResponseBody String updateUser(@RequestBody Usuario usuarioCoordenadas, @PathVariable long id){
        Usuario user = usuarioRepository.findById(id).get();
        user.setLatitude(usuarioCoordenadas.getLatitude());
        user.setLongitude(usuarioCoordenadas.getLongitude());
        usuarioRepository.save(user);
        return "Update";

    }


















}
