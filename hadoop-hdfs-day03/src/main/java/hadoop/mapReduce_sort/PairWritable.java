package hadoop.mapReduce_sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PairWritable implements WritableComparable<PairWritable> {

    private  String first;

    private int second;

    public PairWritable() {
    }

    /**
     * 重写比较器
     * @param other
     * @return
     */
    public int compareTo(PairWritable other) {//这个方法只接受三种情况  =0  >0  <0
        //先比较first，如果first相同在比较second
        int i = this.first.compareTo(other.first);//abc  abc   会一次比较
        if(i==0){
            return this.second-other.second;
        }
        return i;
    }

    /**
     * 序列化
     * @param dataOutput
     * @throws IOException
     */
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(first);
        dataOutput.writeInt(second);
    }

    /**
     * 反序列化
     * @param dataInput
     * @throws IOException
     */
    public void readFields(DataInput dataInput) throws IOException {
        this.first= dataInput.readUTF();
        this.second=dataInput.readInt();
    }

    @Override
    public String toString() {
        return first + '\t' + second ;
    }

    public PairWritable(String first, int second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
