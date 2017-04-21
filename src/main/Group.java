package main;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author Maxime Guillod
 */
class Group {

    private final String sender;
    private Queue<String> receiver;

    public Group(Queue<String> group) {
        this.sender = group.poll();
        receiver = new LinkedBlockingDeque<String>();
        
        while (!group.isEmpty()) {
            receiver.add(group.poll());
        }
    }

    public String getSender() {
        return sender;
    }

    public Queue<String> getReceiver() {
        return receiver;
    }

}
