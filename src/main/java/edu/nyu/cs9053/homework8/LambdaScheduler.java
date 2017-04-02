package edu.nyu.cs9053.homework8;

import javafx.beans.property.adapter.JavaBeanProperty;
import jdk.nashorn.internal.scripts.JO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by saura on 3/31/2017.
 */
public class LambdaScheduler {

    public static List<Job> scheduler(List<Job> jobList){
        if(jobList==null){
            throw new IllegalArgumentException("Expected Job List");
        }
        jobList.sort(new Comparator<Job>() {
            @Override
            public int compare(Job job1, Job job2) {
                return job1.getEndTime()-job2.getEndTime();
            }
        });
        Job prevJob=jobList.get(0);
        List<Job> optimizedJobList=new ArrayList<>();
        optimizedJobList.add(prevJob);
        for (Job currentJob:jobList) {
            if(currentJob==null){
                throw new IllegalArgumentException("Expected Job");
            }
            if(currentJob.getStartTime()>=prevJob.getEndTime()){
                optimizedJobList.add(currentJob);
                prevJob=currentJob;
            }
        }
        return  optimizedJobList;
    }
}
