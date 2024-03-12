package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.Exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//GERA UM CONSTRUTOR PARA TODAS AS VARIÁVEIS
@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {


private final RegistroProprietarioService registroProprietarioService;
private final ProprietarioRepository proprietarioRepository;





    @GetMapping
    public List<Proprietario> listar(){

        List<Proprietario> all = proprietarioRepository.findAll();
        return all;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar( @Valid  @RequestBody Proprietario proprietario){
        return registroProprietarioService.salvar(proprietario);
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){

        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                 @Valid @RequestBody Proprietario proprietario){

        if(!proprietarioRepository.existsById(proprietarioId))
            return ResponseEntity.notFound().build();

        proprietario.setId(proprietarioId);
        registroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietario);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){

        if(!proprietarioRepository.existsById(proprietarioId))
            return ResponseEntity.notFound().build();

        registroProprietarioService.excluir(proprietarioId);

        return ResponseEntity.noContent().build();
    }


}
