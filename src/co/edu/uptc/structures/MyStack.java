package co.edu.uptc.structures;

public class MyStack<T> {

  private MyList<T> list;

  public MyStack() {
    list = new MyList<>();
  }

  public void push(T element) {
    list.add(element);
  }

  public T pop() {
    if (list.isEmpty()) {
      throw new RuntimeException("Empty stack");
    }
    T element = list.get(list.size() - 1);
    list.remove(list.size() - 1);
    return element;
  }

  public T peek() {
    if (list.isEmpty()) {
      throw new RuntimeException("Empty stack");
    }
    return list.get(list.size() - 1);
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

}
