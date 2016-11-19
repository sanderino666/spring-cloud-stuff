package nl.wartenberg.data;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 6459575038066722615L;

	private Long createdAt;
	private Long lastModified;

	public BaseEntity() {
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "BaseEntity{" + "createdAt=" + createdAt + ", lastModified=" + lastModified + '}';
	}
}
