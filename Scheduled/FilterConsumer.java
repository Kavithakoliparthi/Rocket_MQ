package Scheduled;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

public class FilterConsumer 
{
	public static void main(String[] args) throws Exception
	{
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name_4");

		// only subscribe messages have property a, also a >=0 and a <= 3
		consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));

		consumer.registerMessageListener(new MessageListenerConcurrently() 
		{
		    @Override
		    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context)
		    {
		    	 System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
		        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		    }
		});
		consumer.start();
		System.out.println("Filter consumer started");
	}
}
