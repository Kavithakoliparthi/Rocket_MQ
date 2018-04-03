import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer 
{
	public static void main(String[] args)  throws MQClientException, InterruptedException
	{
		DefaultMQProducer producer=new DefaultMQProducer("please_rename_unique_group_name_4");
		producer.start();
		for(int i=0;i<10;i++)
		{
			try
			{
				  final int index=i;
				  Message msg = new Message("TopicTest" , "TagA" ,("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			      SendResult sendResult = producer.send(msg);
			      System.out.printf("%-10d %s%n",index, sendResult);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				Thread.sleep(1000);
			}
		}
		producer.shutdown();
	}
}
