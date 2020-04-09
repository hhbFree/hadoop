package hadoop.mapReduce_flowCount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReduce extends Reducer<Text,FlowCountBean,Text,FlowCountBean> {
    @Override
    protected void reduce(Text key, Iterable<FlowCountBean> values, Context context) throws IOException, InterruptedException {
        int upFlow=0;
        int upCountFlow=0;
        int downFlow=0;
        int downCountFlow=0;
        for (FlowCountBean flowCountBean : values) {
            upFlow+=flowCountBean.getUpFlow();
            upCountFlow+= flowCountBean.getUpCountFlow();
            downFlow+=flowCountBean.getDownFlow();
            downCountFlow+=flowCountBean.getDownCountFlow();
        }
        FlowCountBean flowCountBean=new FlowCountBean();
        flowCountBean.setUpFlow(upFlow);
        flowCountBean.setUpCountFlow(upCountFlow);
        flowCountBean.setDownFlow(downFlow);
        flowCountBean.setDownCountFlow(downCountFlow);

        context.write(key,flowCountBean);
    }
}
