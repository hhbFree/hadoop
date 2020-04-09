package hadoop.mapReduce_table_join;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TableJoinReduce extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
       //首先要区分是哪张表
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
        String frist= StringUtils.EMPTY;
        String second=StringUtils.EMPTY;
        for (Text value : values) {
            if (value.toString().startsWith("p")){//order
                frist=value.toString();
            }else{//product
                second=value.toString();
            }
        }
        if (frist.equals("")){
            context.write(key,new Text("NULL"+"\t"+second));
        }else{
            context.write(key,new Text(frist+"\t"+second));
        }
        
    }
}
