package com.example.com.redcrad;

public class LinkQueue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;
    public LinkQueue() {
        rear = front = null;
    }
    public void enQueue(T data) {
        Node<T> node = new Node<T>(data);
        if (isEmputy())
        {
            front = rear = node;
        }
        else
        {
            rear.setNext(node);
            rear=node;
        }

        size++;
    }
    public T deQueue()
    {
        if (isEmputy()) {
            throw new RuntimeException("队列为空");
        }
        Node<T> delete = front;
        front = delete.getNext();
        delete.setNext(null);
        size--;
        if (size == 0) {
            rear = front;
        }
        return (T) delete.getData();
    }
    public boolean isEmputy() {
        return (front == null && rear == null) ? true : false;
    }
    public int size() {
        return this.size;
    }
    public void clear()
    {
        rear = front = null;
    }
}
