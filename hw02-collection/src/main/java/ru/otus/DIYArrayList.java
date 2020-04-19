package ru.otus;

import java.util.*;

public class DIYArrayList<T> implements List<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private final static int INCREASE_STEP = 2;
    private int size = 0;
    private Object[] items;

    // create with default cap
    public DIYArrayList() {
        this.items = new Object[DEFAULT_CAPACITY];
    }

    // create with defined cap
    public DIYArrayList(int capacity) {
        this.items = new Object[size];
        size = capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T t) {
        if (size == items.length) {
            increaseListSize();
        }
        items[size++] = t;
        return true;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
        return (T) items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal Index: " + index);
        }
        T oldValue = (T) items[index];
        items[index] = element;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void increaseListSize() {
        this.items = Arrays.copyOf(items, items.length * INCREASE_STEP);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public Iterator<T> iterator() {
        return new OwnIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public ListIterator<T> listIterator() {
        return new OwnListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);
        return new DIYArrayList.OwnListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    private class OwnIterator implements Iterator {
        int cursor;
        int lastRet = -1;

        OwnIterator() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] items = DIYArrayList.this.items;
            if (i >= items.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) items[lastRet = i];
        }
    }

    private class OwnListIterator implements ListIterator<T> {
        int cursor;
        int lastRet = -1;

        OwnListIterator(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] items = DIYArrayList.this.items;
            if (i >= items.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) items[lastRet = i];
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = DIYArrayList.this.items;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (T) elementData[lastRet = i];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                DIYArrayList.this.set(lastRet, t);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(T t) {
            try {
                int i = cursor;
                DIYArrayList.this.add(i, t);
                cursor = i + 1;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }
}


