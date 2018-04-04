package Scheduled;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class FilterProducer 
{
	 public static void main(String[] args) throws MQClientException, InterruptedException
	 {
	        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
	        producer.start();
	        try 
	        {
	            for (int i = 0; i <10; i++)
	            {
	                Message msg = new Message("TopicFilter",
	                    "Tag",
	                    ("Hello world"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));

	                msg.putUserProperty("a", String.valueOf(i));
	                SendResult sendResult = producer.send(msg);
	                System.out.printf("%s%n", sendResult);
	            }
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        producer.shutdown();
	    }
}
