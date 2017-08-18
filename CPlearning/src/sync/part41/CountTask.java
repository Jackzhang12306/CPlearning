package sync.part41;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;


public class CountTask extends RecursiveTask<Integer>{

	private static final int THRESHOLD = 2;
	private int start;
	private int end;
	public CountTask(int start ,int end){
		this.start = start;
		this.end = end ;
	}
	
	public static void main(String[] args) {
		
	long start =	System.currentTimeMillis();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		CountTask task = new CountTask(1, 100000000);
		
		Future<Integer> result = forkJoinPool.submit(task);
		try {
			System.out.println(result.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	long middle = System.currentTimeMillis();
		
	System.out.println(middle-start);
	
	
	int sum=0;
	for(int i =1;i<=100000000;i++){
		sum+=i;
	}
	
	System.out.println(System.currentTimeMillis()-middle);
	System.out.println(sum);
	
	
	
	}

	@Override
	protected Integer compute() {
 
		int sum =0;
		boolean canCompute =(end - start )<= THRESHOLD;
		if(canCompute){
			for(int i=start;i<=end;i++){
				sum +=i;
			}
		}else{
			int middle = (start+end)/2;
			CountTask leftTask =new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle+1, end);
			
			leftTask.fork();
			rightTask.fork();
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();
			sum = leftResult + rightResult;
		}
		return sum;
	}

}
