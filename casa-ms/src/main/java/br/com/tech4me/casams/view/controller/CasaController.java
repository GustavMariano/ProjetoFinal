package br.com.tech4me.casams.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.casams.compartilhado.CasaDto;
import br.com.tech4me.casams.service.CasaService;
import br.com.tech4me.casams.view.model.CasaModeloAlteracao;
import br.com.tech4me.casams.view.model.CasaModeloInclusao;
import br.com.tech4me.casams.view.model.CasaModeloResponse;

@RestController
@RequestMapping("/api/casa")
public class CasaController {
    @Autowired
    private CasaService service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }    

    @PostMapping
    public ResponseEntity<CasaModeloResponse> criarCasa(@RequestBody @Valid CasaModeloInclusao Casa) {
        ModelMapper mapper = new ModelMapper();
        CasaDto dto = mapper.map(Casa, CasaDto.class);
        dto = service.criarCasa(dto);
        return new ResponseEntity<>(mapper.map(dto, CasaModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<CasaModeloResponse>> obterTodos() {
        List<CasaDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<CasaModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, CasaModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value="/{dono}/lista")
    public ResponseEntity<List<CasaModeloResponse>> obterPorDono(@PathVariable String dono) {
        List<CasaDto> dtos = service.obterPorDono(dono);

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<CasaModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, CasaModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<CasaModeloResponse> obterPorId(@PathVariable String id) {
        Optional<CasaDto> Casa = service.obterPorId(id);

        if(Casa.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(Casa.get(), CasaModeloResponse.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<CasaModeloResponse> atualizarCasa(@PathVariable String id,
        @Valid @RequestBody CasaModeloAlteracao Casa) {
        ModelMapper mapper = new ModelMapper();
        CasaDto dto = mapper.map(Casa, CasaDto.class);
        dto = service.atualizarCasa(id, dto);

        return new ResponseEntity<>(mapper.map(dto, CasaModeloResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerCasa(@PathVariable String id) {
        service.removerCasa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}