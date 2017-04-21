package main;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Group for a sending (one sender, and many receiver)
 *
 * @author Maxime Guillod
 */
class Group {

    private final String sender;
    private Queue<String> receiver;

    Group(Queue<String> group) {
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

    /**
     * Return if a group is valide. If it as a sender, and 2 or more receiver
     *
     * @return
     */
    public boolean isValide() {
        if (sender == null) {
            return false;
        }
        if (receiver == null) {
            return false;
        }
        return receiver.size() >= 2;
    }

}
