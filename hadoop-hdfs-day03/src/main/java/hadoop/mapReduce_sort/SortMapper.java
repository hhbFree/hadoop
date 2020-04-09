package hadoop.mapReduce_sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * 有key1   value1    转换成 key2      value2
 * <p>
 * <p>
 * LongWritable key1 行偏移量
 * Text value1 行值
 * PairWritable key2 行值
 * Text value2 行值
 */
public class SortMapper extends Mapper<LongWritable, Text, PairWritable, Text> {
    Logger logger = Logger.getLogger(SortMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //自定义计数器
        Counter counter = context.getCounter("MR_COUNT", "MapRecordCounter");
        counter.increment(1l);


        //1.对每一行数据进行拆分，然后封装到pairwirtable对象中，作为key2
        String[] split = value.toString().split(",");
        PairWritable pairWritable = new PairWritable();
        pairWritable.setFirst(split[0]);
        pairWritable.setSecond(Integer.parseInt(split[1]));
        //2.将key2和value2写入上下文中
        context.write(pairWritable, value);
    }
}
