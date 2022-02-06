package br.com.tech4me.casams.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.com.tech4me.casams.model.Casa;

@Repository
public interface CasaRepositorio extends MongoRepository<Casa, String> {

    List<Casa> findByDono(String dono);
}
