package nl.wartenberg.data;

import java.sql.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	@CreatedDate
	private Date createdAt;

	@LastModifiedDate
	private Date lastModified;

	public BaseEntity() {
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "BaseEntity{" + "createdAt=" + createdAt + ", lastModified=" + lastModified + '}';
	}
}
