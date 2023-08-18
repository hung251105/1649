package interfaces;

public interface AbstractQueue<E> extends Iterable<E> {
    void enqueue(E element);

    E dequeue();

    E peek();

    int size();

    boolean isEmpty();
}
