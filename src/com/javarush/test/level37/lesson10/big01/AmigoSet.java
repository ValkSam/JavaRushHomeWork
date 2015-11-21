package com.javarush.test.level37.lesson10.big01;

import java.io.*;
import java.util.*;

/**
 * Created by Valk on 13.07.15.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Cloneable, Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AmigoSet<Integer> amigoSet = new AmigoSet<>();
        amigoSet.add(1);
        amigoSet.add(2);
        amigoSet.add(3);
        System.out.println(amigoSet.map);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(amigoSet);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        AmigoSet<Integer> amigoSetRecovered = (AmigoSet)ois.readObject();
        System.out.println(amigoSetRecovered.map);
    }

    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map; //не можем сериализировать, т.к. Object не сириализируется

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>((int) Math.max(16, collection.size()/0.75f));
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return (this.map.put(e, PRESENT) != null);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return (map.remove(o) != null);
    }

    @Override
    public Object clone() throws InternalError {
        AmigoSet<E> result = null;
        try {
            result = (AmigoSet<E>)super.clone();
            result.map = (HashMap<E, Object>)this.map.clone();
        } catch (CloneNotSupportedException e) {
            new InternalError();
        }
        return result;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(HashMapReflectionHelper.<Integer>callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.<Float>callHiddenMethod(map, "loadFactor"));
        out.writeObject(new HashSet<Integer>((Collection<Integer>) map.keySet()));
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        addAll((Collection)in.readObject());
    }


}
