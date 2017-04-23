package nl.wartenberg.aggregate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import nl.wartenberg.domain.BaseEntity;

@Document(indexName = "aggregate", type = "aggregate", shards = 1, replicas = 0, refreshInterval = "-1")
public class Aggregate extends BaseEntity {

	private static final long serialVersionUID = 3568001162144256916L;

	@Id
	private String objectId;

	private long aggregateId;

	private String applicationId;

	private String type;

	private String createdBy;

	private String lastModifiedBy;

	private long version = 1;

	private boolean latest = true;

	private String data;

	private Map<String, String> metadata = new HashMap<>();

	public Aggregate() {
		super();
	}

	public Aggregate(long aggregateId, String applicationId, String type, String createdBy, String lastModifiedBy,
			long version, boolean latest, String data, Map<String, String> metadata) {
		super();
		this.aggregateId = aggregateId;
		this.applicationId = applicationId;
		this.type = type;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
		this.version = version;
		this.latest = latest;
		this.data = data;
		this.metadata = metadata;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public long getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(long aggregateId) {
		this.aggregateId = aggregateId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isLatest() {
		return latest;
	}

	public void setLatest(boolean latest) {
		this.latest = latest;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public void addMetadata(String key, String value) {
		metadata.put(key, value);
	}

}
