package hadoop.mapReduce_flowCount_sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountSortMapper extends Mapper<LongWritable, Text, FlowCountSortBean,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        FlowCountSortBean flowCountBean=new FlowCountSortBean();
        //获取value2
        String phoneNum=split[0];

        //获取key2
        flowCountBean.setUpFlow(Integer.parseInt(split[1]));
        flowCountBean.setUpCountFlow(Integer.parseInt(split[2]));
        flowCountBean.setDownFlow(Integer.parseInt(split[3]));
        flowCountBean.setDownCountFlow(Integer.parseInt(split[4]));

        //写入上下文
        context.write(flowCountBean,new Text(phoneNum));

    }
}
