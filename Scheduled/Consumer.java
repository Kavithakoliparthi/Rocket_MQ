package Scheduled;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

public class Consumer 
{
	 public static void main(String[] args) throws Exception
	 {
         DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ExampleConsumer");
         consumer.subscribe("TestTopic", "*");
         consumer.registerMessageListener(new MessageListenerConcurrently() 
         {
             @Override
             public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                 for (MessageExt message : messages) 
                 {
                     System.out.println("Receive message[msgId=" + message.getMsgId() + "] "
                             + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
                 }
                 return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
             }
         });
         consumer.start();
     }
}
