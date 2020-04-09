package hadoop.mapReduce_combiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * LongWritable key1 行偏移量
 * Text         value1 行数据
 * Text         value2 每个单词
 * LongWritable 固定值1
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text,LongWritable> {

    /**
     *
    * key1    value1
     * 0      hello,world
     * 11     hello,hadoop
     *
     * key2    value2
     * hello   1
     * world   1
     * hello   1
     * hadoop  1
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        String[] split = line.split(",");

        Text text = new Text();

        LongWritable longWritable = new LongWritable();

        for (String word : split) {
            text.set(word);
            longWritable.set(1);
            context.write(text,longWritable);
        }

    }
}
