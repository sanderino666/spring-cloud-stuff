package nl.wartenberg.aggregate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RepositoryRestResource(collectionResourceRel = "aggregate", path = "aggregate")
public interface AggregateRepository extends ElasticsearchRepository<Aggregate, String> {

	@HystrixCommand
	Page<Aggregate> findByType(@Param("type") String type, Pageable pageable);
}
