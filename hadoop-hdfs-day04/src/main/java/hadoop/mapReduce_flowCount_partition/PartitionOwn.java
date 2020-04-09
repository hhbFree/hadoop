package hadoop.mapReduce_flowCount_partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionOwn extends Partitioner<Text,FlowCountBean> {
    public int getPartition(Text text, FlowCountBean flowCountBean, int i) {
        if(text.toString().startsWith("136")){
            return 0;
        }else if(text.toString().startsWith("137")){
            return 1;
        }else{
            return 2;
        }
    }
}
