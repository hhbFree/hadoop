package hadoop.mapReduce_sort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 有key2  value2   转换成key3   value3
 */
public class SortReduce extends Reducer<PairWritable, Text, PairWritable, NullWritable> {

    //枚举计数器
    public  enum Counter{
        REDUCE_INPUT_RECORDS,REDUCE_INPUT_VAL_NUMS
    }

    /*
    a   1   <a   1,a   1,a   1>
     */
    @Override
    protected void reduce(PairWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //自定义枚举计数器
        //reduce阶段key的个数
        context.getCounter(Counter.REDUCE_INPUT_RECORDS).increment(1l);

        for (Text value : values) {
            //reduce阶段value的个数
            context.getCounter(Counter.REDUCE_INPUT_VAL_NUMS).increment(1l);
            context.write(key, NullWritable.get());
        }
    }
}
