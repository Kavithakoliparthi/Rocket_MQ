package Scheduled;

import java.util.ArrayList;
import java.util.List;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class BatchDemo 
{
	public static void main(String[] args) throws  Exception
	{
        DefaultMQProducer producer = new DefaultMQProducer("BatchProducerGroupName");
        producer.start();

        //If you just send messages of no more than 1MiB at a time, it is easy to use batch
        //Messages of the same batch should have: same topic, same waitStoreMsgOK
        String str = "BatchMessages";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(str, "Tag", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(str, "Tag", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(str, "Tag", "OrderID003", "Hello world 2".getBytes()));

        SendResult result=producer.send(messages);
        System.out.printf("%s%n",result);
    }
}
