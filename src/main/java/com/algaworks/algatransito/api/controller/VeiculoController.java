package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.algatransito.api.representationmodel.VeiculoRepresentationModel;
import com.algaworks.algatransito.api.representationmodel.input.VeiculoInput;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.ApreensaoVeiculoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoRepository veiculoRepository;

    private RegistroVeiculoService registroVeiculoService;

    private final VeiculoAssembler veiculoAassembler;

    private final ApreensaoVeiculoService apreensaoVeiculoService;

    @GetMapping
    public List<VeiculoRepresentationModel> listar(){
        return veiculoAassembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoRepresentationModel> buscar(@PathVariable Long veiculoId){

        return veiculoRepository.findById(veiculoId)
                .map(veiculoAassembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoRepresentationModel cadastrar(@Valid @RequestBody VeiculoInput veiculoInput){
       // return veiculoAassembler.toModel(registroVeiculoService.cadastrar(veiculo));

        Veiculo novoVeiculo = veiculoAassembler.toEntity(veiculoInput);
        Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);

        return veiculoAassembler.toModel(veiculoCadastrado);

    }
    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId){
            apreensaoVeiculoService.apreender(veiculoId);
            }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerApreensao(@PathVariable Long veiculoId){
        apreensaoVeiculoService.removerApreensao(veiculoId);
    }


}
