package com.quadrolord.ejge.entity;

/**
 * Created by Quadrowin on 31.01.2016.
 */
abstract public class AbstractEntity<T extends AbstractItem> {

    /**
     * Возвращает класс экземпляра
     * @return
     */
    abstract public Class<? extends T> getItemClass();

    /**
     * Переброс полей после создания экземпляра
     * @param item
     */
    abstract public void initItem(T item);

}
