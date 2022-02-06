package br.com.tech4me.primeirorest.clienteHTTP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tech4me.primeirorest.compartilhado.Casa;

@FeignClient(name= "casa-ms", fallback = CasasFeignClientFallback.class)
public interface CasasFeignClient {
    @GetMapping(path = "/api/casa/{dono}/lista")
    List<Casa> obterCasas(@PathVariable String dono);   
}

@Component
class CasasFeignClientFallback implements CasasFeignClient {

    @Override
    public List<Casa> obterCasas(String dono) {
        return new ArrayList<>();
    }

}
