package Scheduled;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class Producer 
{
	public static void main(String[] args) throws Exception 
	{
		DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        producer.start();
        int totalMessagesToSend = 10;
        for (int i = 0; i < totalMessagesToSend; i++) 
        {
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            message.setDelayTimeLevel(3);
            producer.send(message);
        }
        producer.shutdown();
    }
}
