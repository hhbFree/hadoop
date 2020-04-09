package hadoop.mapReduce_flowCount_sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowCountSortBean implements WritableComparable<FlowCountSortBean> {
    private Integer upFlow;
    private Integer downFlow;
    private Integer upCountFlow;
    private Integer downCountFlow;

    @Override
    public String toString() {
        return upFlow +
                "\t" + downFlow +
                "\t" + upCountFlow +
                "\t" + downCountFlow;
    }

    public int getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public int getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public int getUpCountFlow() {
        return upCountFlow;
    }

    public void setUpCountFlow(Integer upCountFlow) {
        this.upCountFlow = upCountFlow;
    }

    public int getDownCountFlow() {
        return downCountFlow;
    }

    public void setDownCountFlow(Integer downCountFlow) {
        this.downCountFlow = downCountFlow;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(upFlow);
        dataOutput.writeInt(upCountFlow);
        dataOutput.writeInt(downFlow);
        dataOutput.writeInt(downCountFlow);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow=dataInput.readInt();
        this.upCountFlow=dataInput.readInt();
        this.downFlow=dataInput.readInt();
        this.downCountFlow=dataInput.readInt();
    }

    public int compareTo(FlowCountSortBean other) {
        return this.upFlow.compareTo(other.getUpFlow());
    }
}
