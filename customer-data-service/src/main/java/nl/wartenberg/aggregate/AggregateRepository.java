package nl.wartenberg.aggregate;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "aggregate", path = "aggregate")
public interface AggregateRepository extends MongoRepository<Aggregate, ObjectId> {
	List<Aggregate> findByType(@Param("type") String type);
}
