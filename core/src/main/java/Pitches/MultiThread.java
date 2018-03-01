package Pitches;

/**
 * Created by Sean Murphy on 12/6/2017.
 */
class running implements Runnable {

    private Thread thread;
    private String threadName;
    private FrequencyDetector detector;
    private boolean bool;

    running( String name, FrequencyDetector d) {
        threadName = name;
        detector = d;
    }

    public void run() {
        try {
            detector.start();
            String note = detector.getFinalNote();
            boolean flag = false;
            while(!flag){
                Thread.sleep(1000);
                if(bool = true){
                    flag = true;
                }
            }
        } catch (InterruptedException e){
            System.out.println("Thread " + threadName + " interrupted.");
        }
    }

    public void start(){
        if(thread == null){
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    public void stop() {
        if(thread != null){
            thread.stop();
        }
    }
}

public class MultiThread {

    private String threadName;
    private FrequencyDetector detector;
    private running thread;

    public MultiThread(String str, FrequencyDetector d) {
        threadName = str;
        detector = d;
    }

    public void go(){
        thread = new running(threadName, detector);
        thread.start();
    }

    public void stop(){
        thread.stop();
    }
}
