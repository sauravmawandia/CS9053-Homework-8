package edu.nyu.cs9053.homework8;
/**
 * Created by saura on 3/31/2017.
 */
public class Job {
    private final int jobId;
    private final int startTime;
    private final int endTime;
    private final int cost;

    public  Job(int jobId,int startTime, int endTime){

        this(jobId,startTime,endTime,0);
    }
    public Job(int jobId,int startTime, int endTime, int cost) {
        this.jobId=jobId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getCost() {
        return cost;
    }
}
