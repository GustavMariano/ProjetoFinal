package br.com.tech4me.casams.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.casams.compartilhado.CasaDto;
import br.com.tech4me.casams.model.Casa;
import br.com.tech4me.casams.repository.CasaRepositorio;

@Service
public class CasaServiceImpl implements CasaService {
    @Autowired
    private CasaRepositorio repo;

    @Override
    public CasaDto criarCasa(CasaDto casa) {
        return salvarCasa(casa);
    }

    @Override
    public List<CasaDto> obterTodos() {
        List<Casa> casas = repo.findAll();

        return casas.stream()
            .map(casa -> new ModelMapper().map(casa, CasaDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<CasaDto> obterPorId(String id) {
        Optional<Casa> casa = repo.findById(id);

       if(casa.isPresent()) {
           return Optional.of(new ModelMapper().map(casa.get(), CasaDto.class));
       }

       return Optional.empty();
    }

    @Override
    public List<CasaDto> obterPorDono(String dono) {
        List<Casa> casas = repo.findByDono(dono);

        return casas.stream()
            .map(casa -> new ModelMapper().map(casa, CasaDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void removerCasa(String id) {
        repo.deleteById(id);
    }


    @Override
    public CasaDto atualizarCasa(String id, CasaDto casa) {
        casa.setId(id);
        return salvarCasa(casa);
    }

    private CasaDto salvarCasa(CasaDto casa) {
        ModelMapper mapper = new ModelMapper();
        Casa casaEntidade = mapper.map(casa, Casa.class);
        casaEntidade = repo.save(casaEntidade);

        return mapper.map(casaEntidade, CasaDto.class);
    }
    
}