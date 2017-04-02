package edu.nyu.cs9053.homework8;

import java.util.*;

/**
 * Created by saura on 3/31/2017.
 */
public class LambdaWeightedScheduler {

    public static List<Job> weightedScheduler(List<Job> jobList) {
        if (jobList == null || jobList.size() == 0) {
            throw new IllegalArgumentException("Expecting Job List");
        }
        Collections.sort(jobList, new Comparator<Job>() {
            public int compare(Job job1, Job job2) {
                return job1.getEndTime() - job2.getEndTime();
            }
        });
        List<Integer> costList=new ArrayList<>(Collections.nCopies(jobList.size()+1, 0));
        List<Integer> indexesOfLastNonConflictingJob=new ArrayList<>(jobList.size()+1);
        // Dynamic Programming to find maximum profit
        for(Job currentJob:jobList){
            if(currentJob==null){
                throw new IllegalArgumentException("Expecting a job");
            }
            int lastNonConflictingIndex = searchForLatestNonConflictingJob(jobList, currentJob.getStartTime(), 0, jobList.size());
            Integer maxWeight = Math.max(costList.get(lastNonConflictingIndex+1) + currentJob.getCost(), costList.get(jobList.indexOf(currentJob)));
            indexesOfLastNonConflictingJob.add(lastNonConflictingIndex);
            costList.set(jobList.indexOf(currentJob)+1,maxWeight);
        }
        return optimizedJobList(jobList,indexesOfLastNonConflictingJob,costList);
    }

    private static List<Job> optimizedJobList(List<Job> jobList,List<Integer> indexesOfLastNonConflictingJob, List<Integer> costList){
        int iterator=jobList.size()-1;
        List<Job> optimizedJobList = new ArrayList<>();
        while(iterator>=0){
            Job job=jobList.get(iterator);
            int lastJobIndex=indexesOfLastNonConflictingJob.get(iterator);
            if (job.getCost() + costList.get(lastJobIndex+1) >= costList.get(iterator)) {
                optimizedJobList.add(job);
                iterator=lastJobIndex;
            } else {
                iterator--;
            }
        }
        return optimizedJobList;
    }
    private static int searchForLatestNonConflictingJob(List<Job> jobList, int key, int left, int right) {
        if (left > right) {
            return right;
        }
        else {
            int mid = (left + right) / 2;
            if (jobList.get(mid).getEndTime() == key) {
                return mid;
            }
            else if (jobList.get(mid).getEndTime() > key) {
                return searchForLatestNonConflictingJob(jobList, key, left, mid - 1);
            }
            else {
                return searchForLatestNonConflictingJob(jobList, key, mid + 1, right);
            }
        }
    }
}
