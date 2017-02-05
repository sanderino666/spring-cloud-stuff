package nl.wartenberg.api.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.wartenberg.aggregate.Aggregate;

@RestController
@RequestMapping(path = "/api/v1")
public class DataSearchController {

	private static final String DATA_PATH = "data.";

	@Autowired
	private MongoTemplate template;

	@GetMapping(path = "/searchData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Aggregate> searchData(HttpServletRequest request, Pageable pageable) {
		final Map<String, String> dataParameters = parseRequestParameters(request);

		final Query query = new Query();
		query.with(pageable);

		for (final Entry<String, String> entry : dataParameters.entrySet()) {
			query.addCriteria(Criteria.where(DATA_PATH + entry.getKey()).is(entry.getValue()));
		}

		return template.find(query, Aggregate.class, "aggregate");

	}

	private Map<String, String> parseRequestParameters(HttpServletRequest request) {
		final Map<String, String[]> parameters = request.getParameterMap();

		final Map<String, String> dataParameters = new HashMap<>();
		for (final Entry<String, String[]> entry : parameters.entrySet()) {
			if (entry.getKey().startsWith("data_")) {
				dataParameters.put(entry.getKey().replaceAll("data_", ""), entry.getValue()[0]);
			}
		}
		return dataParameters;
	}
}
