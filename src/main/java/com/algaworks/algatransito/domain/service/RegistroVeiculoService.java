package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.Exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final RegistroProprietarioService registroProprietarioService;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo){
        if(novoVeiculo.getId() != null){
            throw new NegocioException("Veiculo a ser cadastrado não deve possuir um código com ID");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo))
                .isPresent();
        if(placaEmUso){
            throw new NegocioException("Já existe um veículo cadastrado com essa placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());
        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());

        return  veiculoRepository.save(novoVeiculo);
    }

}