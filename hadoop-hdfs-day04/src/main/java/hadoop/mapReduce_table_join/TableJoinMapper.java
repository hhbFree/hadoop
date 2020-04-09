package hadoop.mapReduce_table_join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TableJoinMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //先判断数据是哪来的文件
        FileSplit fileSplit=(FileSplit) context.getInputSplit();

        String fileName = fileSplit.getPath().getName();
        /**
         * order.txt
         * 1001,20150710,p0001,2
         * 1002,20150710,p0002,3
         * 1002,20150710,p0003,3
         *
         * product.txt
         * p0001,小米5,1000,2000
         * p0002,锤子T1,1000,3000
         */
        if (fileName.equals("orders.txt")){
            //获取pid
            String[] split = value.toString().split(",");
            context.write(new Text(split[2]),value);
        }else{
            String[] split = value.toString().split(",");
            context.write(new Text(split[0]),value);
        }

    }
}
