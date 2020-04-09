package hadoop.mapReduce_flowCount_partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable, Text,Text, FlowCountBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        //拆分手机号
        String phoneNum = split[1];

        //拆分流量字段
        FlowCountBean flowCountBean=new FlowCountBean();
        flowCountBean.setUpFlow(Integer.parseInt(split[6]));
        flowCountBean.setUpCountFlow(Integer.parseInt(split[7]));
        flowCountBean.setDownFlow(Integer.parseInt(split[8]));
        flowCountBean.setDownCountFlow(Integer.parseInt(split[9]));

        //将数据传到上下文
        context.write(new Text(phoneNum),flowCountBean);
    }
}
