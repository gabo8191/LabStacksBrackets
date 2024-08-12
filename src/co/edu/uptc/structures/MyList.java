package co.edu.uptc.structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<T> implements List<T> {
  private Node<T> head;

  @Override
  public int size() {
    int size = 0;
    Node<T> current = head;
    while (current != null) {
      size++;
      current = current.getNext();
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  public static <E> boolean isInstanceOf(Class<E> clazz, Object o) {
    return clazz.isInstance(o);
  }

  @Override
  public boolean contains(Object o) {

    if (o == null) {
      throw new NullPointerException();
    }

    if (!isInstanceOf(o.getClass(), head.getData())) {
      throw new ClassCastException();
    }

    Node<T> current = head;
    while (current != null) {
      if (current.getData().equals(o)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> current = head;

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public T next() {
        T data = current.getData();
        current = current.getNext();
        return data;
      }
    };

  }

  @Override
  public Object[] toArray() {
    Node<T> current = head;
    Object[] array = new Object[size()];
    for (int i = 0; i < size(); i++) {
      array[i] = current.getData();
      current = current.getNext();
    }
    return array;
  }

  @Override
  public boolean add(T e) {
    if (e == null) {
      throw new NullPointerException();
    }

    if (head != null && !isInstanceOf(e.getClass(), head.getData())) {
      throw new ClassCastException();
    }

    Node<T> newNode = new Node<T>(e);
    if (head == null) {
      head = newNode;
    } else {
      Node<T> current = head;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);
    }
    return true;
  }

  @Override
  public boolean remove(Object o) {

    if (o == null) {
      throw new NullPointerException();
    }

    if (head != null && !isInstanceOf(o.getClass(), head.getData())) {
      throw new ClassCastException();
    }

    if (head == null) {
      return false;
    }
    if (head.getData().equals(o)) {
      head = head.getNext();
      return true;
    }
    Node<T> current = head;
    while (current.getNext() != null) {
      if (current.getNext().getData().equals(o)) {
        current.setNext(current.getNext().getNext());
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {

    if (c == null) {
      throw new NullPointerException();
    }

    for (Object o : c) {
      if (head != null && !isInstanceOf(o.getClass(), head.getData())) {
        throw new ClassCastException();
      }
    }

    for (int i = 0; i < c.size(); i++) {
      if (!contains(c.toArray()[i])) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {

    if (c == null) {
      throw new NullPointerException();
    }

    for (T e : c) {
      if (head != null && !isInstanceOf(e.getClass(), head.getData())) {
        throw new ClassCastException();
      }
    }

    for (T e : c) {
      add(e);
    }
    return true;
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {

    if (c == null) {
      throw new NullPointerException();
    }

    for (T e : c) {
      if (head != null && !isInstanceOf(e.getClass(), head.getData())) {
        throw new ClassCastException();
      }
    }

    if (index < 0 || index >= size()) {
      return false;
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    for (T e : c) {
      Node<T> newNode = new Node<T>(e);
      newNode.setNext(current.getNext());
      current.setNext(newNode);
      current = newNode;
    }
    return true;
  }

  @Override
  public boolean removeAll(Collection<?> c) {

    if (c == null) {
      throw new NullPointerException();
    }

    for (Object o : c) {
      if (head != null && !isInstanceOf(o.getClass(), head.getData())) {
        throw new ClassCastException();
      }
    }

    for (int i = 0; i < c.size(); i++) {
      remove(c.toArray()[i]);
    }
    return true;
  }

  @Override
  public boolean retainAll(Collection<?> c) {

    if (c == null) {
      throw new NullPointerException();
    }

    for (Object o : c) {
      if (head != null && !isInstanceOf(o.getClass(), head.getData())) {
        throw new ClassCastException();
      }
    }

    Node<T> current = head;
    while (current != null) {
      if (!c.contains(current.getData())) {
        remove(current.getData());
      }
      current = current.getNext();
    }
    return true;
  }

  @Override
  public void clear() {
    Node<T> current = head;
    while (current != null) {
      Node<T> next = current.getNext();
      current.setNext(null);
      current = next;
    }
    head = null;
  }

  @Override
  public T get(int index) {

    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    }

    Node<T> current = this.head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getData();
  }

  @Override
  public T set(int index, T element) {

    if (element == null) {
      throw new NullPointerException();
    }

    if (head != null && !isInstanceOf(element.getClass(), head.getData())) {
      throw new ClassCastException();
    }

    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    T data = current.getData();
    current.setData(element);
    return data;
  }

  @Override
  public void add(int index, T element) {

    if (element == null) {
      throw new NullPointerException();
    }

    if (head != null && !isInstanceOf(element.getClass(), head.getData())) {
      throw new ClassCastException();
    }

    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    }

    if (index == 0) {
      Node<T> newNode = new Node<T>(element);
      newNode.setNext(head);
      head = newNode;
    } else {
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      Node<T> newNode = new Node<T>(element);
      newNode.setNext(current.getNext());
      current.setNext(newNode);
    }
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    }
    if (index == 0) {
      T data = head.getData();
      head = head.getNext();
      return data;
    }
    Node<T> current = head;
    for (int i = 0; i < index - 1; i++) {
      current = current.getNext();
    }
    T data = current.getNext().getData();
    current.setNext(current.getNext().getNext());
    return data;
  }

  @Override
  public int indexOf(Object o) {

    if (o == null) {
      throw new NullPointerException();
    }

    if (head != null && !isInstanceOf(o.getClass(), head.getData())) {
      throw new ClassCastException();
    }

    Node<T> current = head;
    int index = 0;
    while (current != null) {
      if (current.getData().equals(o)) {
        return index;
      }
      current = current.getNext();
      index++;
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {

    if (o == null) {
      throw new NullPointerException();
    }

    if (head != null && !isInstanceOf(o.getClass(), head.getData())) {
      throw new ClassCastException();
    }

    Node<T> current = head;
    int index = 0;
    int lastIndex = -1;
    while (current != null) {
      if (current.getData().equals(o)) {
        lastIndex = index;
      }
      current = current.getNext();
      index++;
    }
    return lastIndex;
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {

    if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
      throw new IndexOutOfBoundsException();
    }

    List<T> subList = new MyList<T>();
    Node<T> current = head;
    for (int i = 0; i < fromIndex; i++) {
      current = current.getNext();
    }
    for (int i = fromIndex; i < toIndex; i++) {
      subList.add(current.getData());
      current = current.getNext();
    }
    return subList;

  }

  @SuppressWarnings("unchecked")
  @Override
  public <E> E[] toArray(E[] a) {

    if (a == null) {
      throw new NullPointerException();
    }

    if (a.length < size()) {
      throw new ArrayStoreException();
    }

    Iterator<T> iterator = iterator();
    int i = 0;
    while (iterator.hasNext()) {
      a[i] = (E) iterator.next();
      i++;
    }
    return a;
  }

  @Override
  public ListIterator<T> listIterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    throw new UnsupportedOperationException();
  }

}
