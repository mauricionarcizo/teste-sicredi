package br.com.teste.sicredi.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.sicredi.entities.AssociadoVoto;

@Repository
public interface AssociadoVotoRepository extends MongoRepository<AssociadoVoto, ObjectId> {

	@Query(value= "{'idSessao': ?0, 'cpf': ?1 }", exists = true)
	public boolean existsVotoComputado(String idSessao, String cpf);

	public List<AssociadoVoto> findAllByIdSessao(String idSessao);

}
