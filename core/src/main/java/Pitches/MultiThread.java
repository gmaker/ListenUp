package Pitches;

/**
 * Created by Sean Murphy on 12/6/2017.
 */
class running implements Runnable {

    private Thread thread;
    private String threadName;
    private FrequencyDetector detector;

    running( String name, FrequencyDetector d) {
        threadName = name;
        detector = d;
    }

    public void run() {
        detector.start();
    }

    public void start(){
        if(thread == null){
            thread = new Thread(this, threadName);
            thread.start();
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
}
