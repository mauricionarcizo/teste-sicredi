package br.com.teste.sicredi.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.sicredi.entities.Sessao;

@Repository
public interface SessaoRepository extends MongoRepository<Sessao, ObjectId> {

}
