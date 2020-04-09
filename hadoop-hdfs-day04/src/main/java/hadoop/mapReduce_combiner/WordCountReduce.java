package hadoop.mapReduce_combiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Text key2  单词    hello
 * LongWritable value2  单词出现的集合 <1,1,1>
 * Text key3 单词     hello
 * LongWritable value3 单词出现的个数 3
 */
public class WordCountReduce extends Reducer<Text, LongWritable,Text,LongWritable> {
    /**
     * key 是出现的单词
     * count 单词出现的次数  value3
     * @param key
     * @param values value2
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count=0;
        for (LongWritable value : values) {
            count+=value.get();
        }
        context.write(key,new LongWritable(count));
    }
}
