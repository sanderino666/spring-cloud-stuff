package nl.wartenberg.aggregate;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@PreAuthorize("hasAuthority('AGGREGATE')")
@RepositoryRestResource(collectionResourceRel = "aggregate", path = "aggregate")
public interface AggregateRepository extends MongoRepository<Aggregate, ObjectId> {

	@HystrixCommand
	Page<Aggregate> findByType(@Param("type") String type, Pageable pageable);
}
