class ProcessEntity {
    private int processId;
    private String processName;
    private int executionTime;

    public ProcessEntity(int processId, String processName, int executionTime) {
        this.processId = processId;
        this.processName = processName;
        this.executionTime = executionTime;
    }

    public int getProcessId() {
        return processId;
    }

    public String getProcessName() {
        return processName;
    }

    public int getExecutionTime() {
        return executionTime;
    }
}

class PriorityQueueNode {
    ProcessEntity process;
    PriorityQueueNode next;

    public PriorityQueueNode(ProcessEntity process) {
        this.process = process;
        this.next = null;
    }
}

class PriorityQueue {
    private PriorityQueueNode front;

    public PriorityQueue() {
        front = null;
    }

    public void insert(ProcessEntity process) {
        PriorityQueueNode newNode = new PriorityQueueNode(process);

        // Inserting a process at beginning if it has less execution time
        if (front == null || process.getExecutionTime() < front.process.getExecutionTime()) {
            newNode.next = front;
            front = newNode;
        } else {
            // Finding the appropriate position to insert the process based on execution
            // time
            PriorityQueueNode current = front;
            while (current.next != null && current.next.process.getExecutionTime() <= process.getExecutionTime()) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public ProcessEntity remove() {
        if (isEmpty()) {
            return null;
        }
        ProcessEntity removedProcess = front.process;
        front = front.next;
        return removedProcess;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

public class sjf {
    public static void main(String[] args) {
        PriorityQueue sjfQueue = new PriorityQueue();

        // Adding processes to the SJF queue
        sjfQueue.insert(new ProcessEntity(1, "ProcessA", 6));
        sjfQueue.insert(new ProcessEntity(2, "ProcessB", 15));
        sjfQueue.insert(new ProcessEntity(3, "ProcessC", 8));
        sjfQueue.insert(new ProcessEntity(4, "ProcessD", 11));

        // Perform SJF scheduling
        while (!sjfQueue.isEmpty()) {
            ProcessEntity nextProcess = sjfQueue.remove();
            System.out
                    .println("Executing " + nextProcess.getProcessName() + " (ID: " + nextProcess.getProcessId() + ")");
        }
    }
}
