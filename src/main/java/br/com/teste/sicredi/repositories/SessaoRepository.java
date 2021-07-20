package br.com.teste.sicredi.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.sicredi.entities.Sessao;

@Repository
public interface SessaoRepository extends MongoRepository<Sessao, ObjectId> {

	public Optional<Sessao> findBySessaoId(String sessaoId);

}
