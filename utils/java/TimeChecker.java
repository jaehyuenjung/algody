package utils.java;

public class TimeChecker {
    private long startTime;
    private long endTime;

    public TimeChecker() {
        this.startTime = 0;
        this.endTime = 0;
    }

    public void run(){
        this.startTime = System.currentTimeMillis();
    }

    public void stop(){
        this.endTime = System.currentTimeMillis();
    }

    public void print(){
        long elapsedTime = endTime - startTime;
        System.out.printf("실행 시간: %d ms\n", elapsedTime);
    }
}
