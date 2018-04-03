import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class BroadcastConsumerGroupName 
{
	public static void main(String[] args) throws MQClientException 
	{
		DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("consumer");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe("TopicTest", "*");
		consumer.registerMessageListener(new MessageListenerConcurrently()
		{
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> arg0, ConsumeConcurrentlyContext arg1) 
			{
				 System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), arg0);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
		System.out.println("BroadCast consumer GroupName started");
	}
}
