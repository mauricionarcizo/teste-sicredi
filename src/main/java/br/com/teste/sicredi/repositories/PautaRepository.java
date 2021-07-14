package br.com.teste.sicredi.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.sicredi.entities.Pauta;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, ObjectId> {

}