package hadoop.mapReduce_flowCount_partition;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowCountBean implements Writable {
    private int upFlow;
    private int downFlow;
    private int upCountFlow;
    private int downCountFlow;

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

    public void setUpFlow(int upFlow) {
        this.upFlow = upFlow;
    }

    public int getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(int downFlow) {
        this.downFlow = downFlow;
    }

    public int getUpCountFlow() {
        return upCountFlow;
    }

    public void setUpCountFlow(int upCountFlow) {
        this.upCountFlow = upCountFlow;
    }

    public int getDownCountFlow() {
        return downCountFlow;
    }

    public void setDownCountFlow(int downCountFlow) {
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
}
