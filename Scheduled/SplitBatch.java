package Scheduled;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class SplitBatch 
{
	 public static void main(String[] args) throws Exception
	 {
	        DefaultMQProducer producer = new DefaultMQProducer("BatchProducerGroupName");
	        producer.start();
	        		//large batch
	        String s = "SpiliTest";
	        List<Message> message = new ArrayList<>(10 * 100);
	        for (int i = 0; i < 10 * 100; i++) 
	        {
	            message.add(new Message(s, "Tag", "OrderID" + i, ("Hello world " + i).getBytes()));
	        }
	        		//split the large batch into small ones:
	        ListSplitter splitter = new ListSplitter(message);
	        while (splitter.hasNext())
	        {
	           try 
	           {
	               List<Message>  listItem = splitter.next();
	               SendResult result=producer.send(listItem);
	               System.out.printf("%s%n",result);
	           }
	           catch (Exception e) 
	           {
	               e.printStackTrace();
	           }
	        }
    }
}
class ListSplitter implements Iterator<List<Message>> 
{
    private int sizeLimit = 100 * 100;
    private final List<Message> messages;
    private int currIndex;

    public ListSplitter(List<Message> messages)
    {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() 
    {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() 
    {
        int nextIndex = currIndex;
        int totalSize = 0;
        for (; nextIndex < messages.size(); nextIndex++) 
        {
            Message message = messages.get(nextIndex);
            int tmpSize = message.getTopic().length() + message.getBody().length;
            Map<String, String> properties = message.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) 
            {
                tmpSize += entry.getKey().length() + entry.getValue().length();
            }
            tmpSize = tmpSize + 20; //for log overhead
            if (tmpSize > sizeLimit) 
            {
                //it is unexpected that single message exceeds the sizeLimit
                //here just let it go, otherwise it will block the splitting process
                if (nextIndex - currIndex == 0) 
                {
                    //if the next sublist has no element, add this one and then break, otherwise just break
                    nextIndex++;
                }
                break;
            }
            if (tmpSize + totalSize > sizeLimit)
            {
                break;
            }
            else 
            {
                totalSize += tmpSize;
            }

        }
        List<Message> subList = messages.subList(currIndex, nextIndex);
        currIndex = nextIndex;
        return subList;
    }
}
