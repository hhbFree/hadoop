package hadoop.mapReduce_sort;

import hadoop.mapReduce.WordCountMapper;
import hadoop.mapReduce.WordCountReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JobMain extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), "mapReduce_sort");
        //打包到集群上面运行时候，必须要添加以下配置，指定程序的main函数
        job.setJarByClass(JobMain.class);
        //第一步：读取文本数据类：key1  value1
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("hdfs://192.168.174.100:8020/sort"));

        //第二步：设置mapper类
        job.setMapperClass(SortMapper.class);

        //设置输出类型
        job.setMapOutputKeyClass(PairWritable.class);
        job.setMapOutputValueClass(Text.class);

        //第三四五六步省略(分区，排序，规约，分组)


        //第七步：设置reduce类
        job.setReducerClass(SortReduce.class);
        //设置输出类型
        job.setOutputKeyClass(PairWritable.class);
        job.setOutputValueClass(NullWritable.class);

        //第八步：设置输出类型
        job.setOutputFormatClass(TextOutputFormat.class);

        //输出路径
        TextOutputFormat.setOutputPath(job,new Path("hdfs://192.168.174.100:8020/wordcount_out_sort"));

        boolean b = job.waitForCompletion(true);
        return b?0:1;

    }

    public static void main(String[] args) throws Exception {
        Configuration configuration=new Configuration();
        int i = ToolRunner.run(configuration, new JobMain(), args);
        System.exit(i);
    }
}
