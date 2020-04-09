package hadoop.mapReduce_flowCount_sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountSortReduce extends Reducer<FlowCountSortBean, Text,Text, FlowCountSortBean> {
    @Override
    protected void reduce(FlowCountSortBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value,key);
        }
        
    }
}
