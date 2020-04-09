package hadoop.mapReduce_combiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 规约  减轻reduce阶段的压力，在分布式服务器中先执行了一次
 */
public class WordCombiner extends Reducer<Text, LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count=0;
        for (LongWritable value : values) {
            count+=value.get();
        }
        context.write(key,new LongWritable(count));
    }
}
