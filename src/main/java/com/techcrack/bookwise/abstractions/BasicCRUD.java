package com.techcrack.bookwise.abstractions;

public interface BasicCRUD<E> {
    E register(E entity);
    void remove(long key);
    E update(E entity);
    E get(long key);
}
