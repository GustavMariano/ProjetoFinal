package br.com.tech4me.casams.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.casams.compartilhado.CasaDto;

public interface CasaService {
    CasaDto criarCasa(CasaDto casa);
    List<CasaDto> obterTodos();
    Optional<CasaDto> obterPorId(String id);
    List<CasaDto> obterPorDono(String dono);
    void removerCasa(String id);
    CasaDto atualizarCasa(String id, CasaDto casa);
}