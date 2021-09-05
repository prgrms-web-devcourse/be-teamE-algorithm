package programmers.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DiskController {
	public int solution(int[][] jobs) {
		HardDiskSystem hardDiskSystem = new HardDiskSystem();
		hardDiskSystem.insertJobs(jobs);
		hardDiskSystem.doJobScheduling();
		return hardDiskSystem.getTotalElapsedTime() / jobs.length;
	}
}

class Job {
	private int requestedTime;
	private int workTime;

	Job(int requestedTime, int workTime) {
		this.requestedTime = requestedTime;
		this.workTime = workTime;
	}

	public int getRequestedTime() {
		return this.requestedTime;
	}

	public int getWorkTime() {
		return this.workTime;
	}
}

class HardDiskSystem {
	private PriorityQueue<Job> waitQueue; // sort by requested time in ascending order
	private PriorityQueue<Job> jobQueue; // sort by work time in ascending order
	private int currentTime;
	private int totalElapsedTime;
	private int totalJobNumber;
	private int countJobDone;

	HardDiskSystem() {
		waitQueue = new PriorityQueue<>(Comparator.comparingInt(Job::getRequestedTime));
		jobQueue = new PriorityQueue<>(Comparator.comparingInt(Job::getWorkTime));
		currentTime = 0;
		totalElapsedTime = 0;
		countJobDone = 0;
	}

	public int getTotalElapsedTime() {
		return totalElapsedTime;
	}

	public void insertJobs(int[][] jobs) {
		for (int[] job : jobs) {
			int requestedTime = job[0];
			int workTime = job[1];
			waitQueue.offer(new Job(requestedTime, workTime));
		}
		totalJobNumber = waitQueue.size();
	}

	public void doJobScheduling() {
		while (countJobDone < totalJobNumber) {
			// Schedule jobs to do
			while (!waitQueue.isEmpty() && waitQueue.peek().getRequestedTime() <= currentTime) {
				jobQueue.offer(waitQueue.poll());
			}

			if (jobQueue.isEmpty()) {
				currentTime = waitQueue.peek().getRequestedTime();
				continue;
			}

			// do a job
			Job currentJob = jobQueue.poll();
			currentTime += currentJob.getWorkTime();
			totalElapsedTime += (currentTime - currentJob.getRequestedTime());
			countJobDone++;
		}
	}
}