package hadoop.mapReduce_group;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionerOwn extends Partitioner<Text, LongWritable> {

    /**
     *
     * @param text  key2
     * @param longWritable  value2
     * @param i  reduce的个数
     * @return
     */
    public int getPartition(Text text, LongWritable longWritable, int i) {
        //如果单词分区>=5  就进入第一个分区    第一个reduceTask     reduce编号为0
        if(text.toString().length()>=5){
            return 0;
        }else{
        //如果单词分区<5  就进入第二个分区    第二个reduceTask     reduce编号为1
            return 1;
        }
    }
}
