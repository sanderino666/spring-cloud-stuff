package nl.wartenberg.aggregate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class AggregateSink {
	private static final Logger LOGGER = LoggerFactory.getLogger(AggregateSink.class);

	@StreamListener(Sink.INPUT)
	public void saveAggregate(Message<Aggregate> message) {

		LOGGER.debug("Received: " + message.getPayload());
	}
}
