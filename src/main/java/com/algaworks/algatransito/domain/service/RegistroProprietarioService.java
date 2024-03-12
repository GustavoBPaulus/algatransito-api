package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.Exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

        private final ProprietarioRepository proprietarioRepository;

        public Proprietario buscar(Long proprietarioId){
            Proprietario proprietario = proprietarioRepository.findById(proprietarioId)
            .orElseThrow(() -> new NegocioException("Proprietário não encontrado!"));
        return proprietario;
        }
        @Transactional
        public Proprietario salvar(Proprietario proprietario){
            boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                    .filter(p -> !p.equals(proprietario))
                    .isPresent();

            if(emailEmUso){
                throw new NegocioException("Já existe um proprietário com este email.");
            }
            return proprietarioRepository.save(proprietario);
        }

    public void excluir(Long proprietarioId){

            proprietarioRepository.deleteById(proprietarioId);
    }


}
