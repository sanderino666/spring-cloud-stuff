package nl.wartenberg.listener;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import nl.wartenberg.domain.BaseEntity;

@Component
public class SaveAggregateListener extends AbstractMongoEventListener<BaseEntity> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaveAggregateListener.class);

	private final MessageChannel output;

	@Autowired
	public SaveAggregateListener(MessageChannel output) {
		this.output = output;
	}

	@Override
	public void onBeforeSave(BeforeSaveEvent<BaseEntity> event) {

		final Date timestamp = new Date();

		// Add a timestamp to the created date if it does not yet exist
		if (event.getSource().getCreatedAt() == null) {
			event.getSource().setCreatedAt(timestamp);
		}

		// Update the timestamp to the current time
		event.getSource().setLastModified(timestamp);

		super.onBeforeSave(event);
	}

	@Override
	public void onAfterSave(AfterSaveEvent<BaseEntity> event) {
		LOGGER.info("onAfterSave {}", event);
		output.send(MessageBuilder.withPayload(event.getSource()).build());
	}

	@Override
	public void onAfterDelete(AfterDeleteEvent<BaseEntity> event) {
		LOGGER.info("onAfterDelete {}", event);
	}
}
