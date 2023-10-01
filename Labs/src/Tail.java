import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tail extends FileProcessor<List<String>>{
    private List<String> lastLines = new ArrayList<>();
    private Queue<String> queue = new LinkedList<>();
    private int numLastLines;
    public Tail(int n){
        this.numLastLines = n;
    }
    @Override
    protected void startFile() {

    }

    @Override
    protected void processLine(String line) {
        queue.add(line);
        if (queue.size() > numLastLines){
            queue.poll();
        }

    }

    @Override
    protected List<String> endFile() {
        lastLines.addAll(queue);
        return lastLines;
    }
}
