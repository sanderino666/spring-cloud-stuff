package nl.wartenberg.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import nl.wartenberg.aggregate.Aggregate;
import nl.wartenberg.aggregate.AggregateRepository;

@Service
public class DatabaseInitializer {
	private static final Logger LOG = LoggerFactory.getLogger(DatabaseInitializer.class);

	private final AggregateRepository aggregateRepository;

	@Autowired
	public DatabaseInitializer(AggregateRepository aggregateRepository) {
		this.aggregateRepository = aggregateRepository;
	}

	public void populate() {
		// Clear existing data
		aggregateRepository.deleteAll();

		final List<Aggregate> aggregates = new ArrayList<>();
		final Random rand = new Random();

		final int max_zaak_records = 5000; // 500000
		final int max_aanvraag_records = 5000;
		final int max_beoordeling_records = 5000;

		// Create aggregates of type 'Zaak'
		LOG.info("Start creating " + max_zaak_records + " Zaak aggregates");
		for (int zaak = 0; zaak < max_zaak_records; zaak++) {
			final Map<String, String> metadata = new HashMap<>();
			metadata.put("zaak1", String.valueOf(rand.nextInt(100)));
			metadata.put("zaak2", String.valueOf(rand.nextInt(100)));
			metadata.put("zaak3", String.valueOf(rand.nextInt(100)));

			final Aggregate aggregate = new Aggregate(zaak, "export-demo", "Zaak", "swartenberg", null, 1L, true,
					createData(), metadata);

			aggregates.add(aggregate);
		}

		// Create aggregates of type 'Aanvraag'
		LOG.info("Start creating " + max_aanvraag_records + " Aanvraag aggregates");
		for (int zaak = 0; zaak < max_aanvraag_records; zaak++) {
			final Map<String, String> metadata = new HashMap<>();
			metadata.put("aanvraag1", String.valueOf(rand.nextInt(100)));
			metadata.put("aanvraag2", String.valueOf(rand.nextInt(100)));
			metadata.put("aanvraag3", String.valueOf(rand.nextInt(100)));

			final Aggregate aggregate = new Aggregate(zaak, "export-demo", "Aanvraag", "swartenberg", null, 1L, true,
					createData(), metadata);

			aggregates.add(aggregate);
		}

		// Create aggregates of type 'Beoordeling'
		LOG.info("Start creating " + max_beoordeling_records + " Beoordeling aggregates");
		for (int zaak = 0; zaak < max_beoordeling_records; zaak++) {
			final Map<String, String> metadata = new HashMap<>();
			metadata.put("beoordeling1", String.valueOf(rand.nextInt(100)));
			metadata.put("beoordeling2", String.valueOf(rand.nextInt(100)));
			metadata.put("beoordeling3", String.valueOf(rand.nextInt(100)));

			final Aggregate aggregate = new Aggregate(zaak, "export-demo", "Beoordeling", "swartenberg", null, 1L, true,
					createData(), metadata);

			aggregates.add(aggregate);
		}

		LOG.info("Saving all aggregates");
		aggregateRepository.save(aggregates);
		LOG.info("All aggregated are saved");
	}

	private BasicDBObject createData() {
		final Random rand = new Random();

		final String[] names = new String[10];
		names[0] = "Sander Wartenberg";
		names[1] = "Paul Stalenhoef";
		names[2] = "Onno de Groote";
		names[3] = "Maarten Damen";
		names[4] = "Raoul de Haard";
		names[5] = "Martijn van Tilburg";
		names[6] = "Rick Arts";
		names[7] = "Maarten Schadd";
		names[8] = "Geert Graat";
		names[9] = "Martijn van der Blom";

		final String data = "{'name':'" + names[rand.nextInt(10)] + "', 'age':'" + rand.nextInt(1000) + "'}";

		return (BasicDBObject) JSON.parse(data);
	}
}
