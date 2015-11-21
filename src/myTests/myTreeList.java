package myTests;
// задача package com.javarush.test.level20.lesson10.bonus04;
// но с дженериком

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать myTreeList.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class myTreeList<E>
        extends AbstractList<E>
        implements List<E>, Cloneable, Serializable
{

    public static void main(String[] args) throws Exception {
        //List<String> list = new myTreeList();
        myTreeList<String> list = new myTreeList<>();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        for (List<String> line : list.getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+list.first.element);
        System.out.print(" last "+list.last.element);
        System.out.println(" size "+list.size);
        System.out.println("============================");
        System.out.println("deleted "+list.remove("2"));
        System.out.println("deleted "+list.remove("9"));
        System.out.println("----------------------------");
        for (List<String> line : list.getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+list.first.element);
        System.out.print(" last "+list.last.element);
        System.out.println(" size "+list.size);
        System.out.println("============================");
        list.addAll(Arrays.asList("16", "17", "18", "19", "20"));
        for (List<String> line : list.getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+list.first.element);
        System.out.print(" last "+list.last.element);
        System.out.println(" size "+list.size);
        System.out.println("============================");
        System.out.println(" deleted "+list.remove("18"));
        System.out.println(" deleted "+list.remove("19"));
        for (List<String> line : list.getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+list.first.element);
        System.out.print(" last "+list.last.element);
        System.out.println(" size "+list.size);
        System.out.println("============================");
        list.addAll(Arrays.asList("21", "22"));
        for (List<String> line : list.getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+list.first.element);
        System.out.print(" last "+list.last.element);
        System.out.println(" size "+list.size);
        System.out.println("============================");

        System.out.println("Expected 3, actual is " + ((myTreeList) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((myTreeList) list).getParent("11"));
        System.out.println(list.getParent("1"));

        //*******************************************

        System.out.println("***************");
        try {
            myTreeList<String> list1 = (myTreeList) list.clone();
            for (List<String> line : list1.getTree()){
                for (String s : line){
                    System.out.print(s+" ");
                }
                System.out.println();
            }
            System.out.print(" first "+list1.first.element);
            System.out.print(" last "+list1.last.element);
            System.out.println(" size "+list1.size);
            System.out.println("============================");
            for (Iterator<String> iterator = list1.iterator(); iterator.hasNext();){
                System.out.print(iterator.next()+" ");
            }
        } catch (Exception e) {}

        //++++++++++++++++++++++++++++++++++++

        //сериализация

        //ByteArrayOutputStream buf = new ByteArrayOutputStream();
        //ObjectOutputStream oos = new ObjectOutputStream(buf);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:\\q.txt"));
        oos.writeObject(list);
        //ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:\\q.txt"));
        myTreeList<String> list2 = (myTreeList)ois.readObject();
        System.out.println();
        System.out.println(" +++++++++ после сериализации ");
        try {
            for (List<String> line : list2.getTree()){
                for (String s : line){
                    System.out.print(s+" ");
                }
                System.out.println();
            }
            System.out.print(" first "+list2.first.element);
            System.out.print(" last "+list2.last.element);
            System.out.println(" size "+list2.size);
            System.out.println("============================");
            for (Iterator<String> iterator = list2.iterator(); iterator.hasNext();){
                System.out.print(iterator.next()+" ");
            }
        } catch (Exception e) {}






/*
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("============================");
        for (Iterator<String> iterator = list.descendingIterator(); iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("============================");

        for (Iterator<String> iterator = list.descendingIterator(); iterator.hasNext();){
            iterator.next();
            iterator.remove();
            for (Iterator<String> iter = list.iterator(); iter.hasNext();){
                System.out.print(iter.next()+" ");
            }
            System.out.println();
        }
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("============================");
        */
        /*
        //удаление через один в прямой итерации
        list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        //list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));
        System.out.println();
        for (List<String> line : list.getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.println();
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
            iterator.next();
            if (! iterator.hasNext()) break;
            iterator.next();
            iterator.remove();
            for (Iterator<String> iter = list.iterator(); iter.hasNext();){
                System.out.print(iter.next()+" ");
            }
            System.out.println();
        }
        System.out.println();
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("============================");*/
        /*
        //удаление через один в Обратной итерации
        list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        //list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));
        System.out.println();
        for (List<String> line : list.getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.println();
        for (Iterator<String> iterator = list.descendingIterator(); iterator.hasNext();){
            iterator.next();
            if (! iterator.hasNext()) break;
            iterator.next();
            iterator.remove();
            for (Iterator<String> iter = list.iterator(); iter.hasNext();){
                System.out.print(iter.next()+" ");
            }
            System.out.println();
        }
        System.out.println();
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("============================");
        */
        /*
        //listIterator
        list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        System.out.println();
        for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();){
            String s = iterator.next();
            iterator.set(s+"a");
            if (! iterator.hasNext()) break;
            iterator.next();
            if (! iterator.hasNext()) break;
            iterator.next();
            iterator.remove();
            for (Iterator<String> iter = list.iterator(); iter.hasNext();){
                System.out.print(iter.next()+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("============================");
        ListIterator<String> itr = list.listIterator();
        for (;itr.hasNext();){
            System.out.print(itr.next()+" ");
        }
        System.out.println();
        for (;itr.hasPrevious();){
            System.out.print(itr.previous()+" ");
        }
        System.out.println("============================");
*/

    }


    private int size = 0;

    private Node<E> first = new Node<>(null, null);
    private Node<E> last = first;

    public String getParent(String value) {
        Node<E> node = getNode(value);
        if (node == null) return null;
        if (node == first) return null;
        return (String)node.parent.element;
    }

    private static class Node<E> implements Serializable{
        E element;
        Node<E> parent;
        Node<E> prev;
        Node<E> next;
        Node<E> leftChild;
        Node<E> rightChild;
        Node (E element, Node<E> prev){
            this.element = element;
            this.prev = prev;
        }
        private boolean isSameLevel(Node<E> otherNode){
            int level = 0;
            for (Node<E> node = parent; node != null; node = node.parent) level++;
            int otherLevel = 0;
            for (Node<E> node = otherNode.parent; node != null; node = node.parent) otherLevel++;
            return (level == otherLevel);
        }
    }

    public myTreeList(){}
    public myTreeList(Collection<? extends E> c){
        this();
        addAll(c);
    }

    public E getFirst() {
        if (first == null)
            throw new NoSuchElementException();
        if (first.next == null) //вершину не показываем
            throw new NoSuchElementException();
        return first.next.element;
    }

    public E getLast() {
        if (last == null)
            throw new NoSuchElementException();
        if (last == first) //вершину не показываем
            throw new NoSuchElementException();
        return last.element;
    }

    public boolean add(E element){
        return linkLast(element);
    }

    private boolean linkLast(E element){
        Node<E> newNode;

        newNode = new Node<>(element, last);
        last.next = newNode;
        Node<E> parent = getParentNodeForNewChild();
        newNode.parent = parent;
        if (parent.leftChild == null){
            parent.leftChild = newNode;
        }
        else {
            parent.rightChild = newNode;
        }

        last = newNode;
        size++;
        return true;
    }

    private boolean linkBefore(E element){
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    private Node<E> getParentNodeForNewChild(){
        Node<E> parent = last.parent;
        if (parent == null) { //вершина, потомков нет
            return first;
        }
        if (parent.rightChild == null){ //правая ветка свободна
            return parent;
        }
        else {
            if (parent.leftChild == null) { //если левая на последнем узле ветка пустая - правую смещаем в левую
                parent.leftChild = parent.rightChild;
                parent.rightChild = null;
                return parent;
            }
            return parent.next;  //у родителя две ветки - начинаем заполнять следующего родителя
        }
    }

    public boolean addAll(Collection<? extends E> c){
        Object[] a = c.toArray();
        if (a.length == 0) return false;
        for (Object o : a){
            @SuppressWarnings("unchecked") E e = (E)o;
            add(e);
        }
        return true;
    }

    public boolean remove(Object o){
        Node<E> topNode = getNode(o);
        if (topNode == null) return false;
        return remove(topNode);
    }

    private boolean remove(Node<E> topNode){
        if (topNode == null) throw new NullPointerException("deleting null-list element attempt");
        int oldSize = size;
        deleteSubTree(topNode);
        if (topNode != first) unlink(topNode); //вершину не трогаем - ее как бы нет
        return (oldSize - size) > 0 ;
    }

    public boolean removeAll(Collection<? extends Object> c){
        Object[] a = c.toArray();
        if (a.length == 0) return false;
        int oldSize = size;
        for (Object o : a){
            @SuppressWarnings("unchecked") E e = (E)o;
            remove(e);
        }
        return (oldSize - size) > 0 ;
    }

    public void clear(){
        remove(first);
    }

    private Node<E> getNode(Object o){
        for (Node<E> node = first.next; node != null; node = node.next){ //начинаем не с вершину - вершину не показываем
            if ( ((o == null)&&(node.element == null))||( (o != null)&&(o.equals(node.element)) ) ) {
                return node;
            }
        }
        return null;
    }

    private void deleteSubTree(Node<E> topNode){
        if (topNode == null) return;
        Node<E> node;
        node = findAndDeleteLeftChild(topNode);
        if (node != null) {
            unlink(node);
        }
        node = findAndDeleteRightChild(topNode);
        if (node != null) {
            unlink(node);
        }
    }

    private Node<E> findAndDeleteLeftChild(Node<E> topNode){
        Node<E> node = topNode.leftChild;
        if (node != null) deleteSubTree(node);
        return node;
    }

    private Node<E> findAndDeleteRightChild(Node<E> topNode){
        Node<E> node = topNode.rightChild;
        if (node != null) deleteSubTree(node);
        return node;
    }

    private E unlink(Node<E> node) {
        if (node == null) return null;
        E el = node.element;
        if (node.parent != null) {
            if (node.parent.leftChild == node) node.parent.leftChild = null;
            else node.parent.rightChild = null;
        }
        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
        if (node.next == null) last = node.prev;
        node.element = null;
        size--;
        return el; //онулили только входящие ссылки. Исходящие ссылки нельзя обнулять, т.к. они используются в итераторе
    }

    //итераторы
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        Node<E> currNode = first.next;
        Node<E> lastRetNode = null;

        public boolean hasNext() {
            return (currNode != null);
        }

        public E next() {
            if (currNode != null) {
                lastRetNode = currNode;
                currNode = currNode.next;
                return lastRetNode.element;
            }
            else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lastRetNode == null) //реагируем на попытку повторного в текущей итерации удаление
                throw new IllegalStateException();
            if (last == first)
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            try {
                myTreeList.this.remove(lastRetNode);
                currNode = lastRetNode.next; //переустанавливаем currNode, т.к. при удалении под-дерева currNode может оказаться == null, хотя до удаления был != null. Такое будет, если удалить правую узел, при отсутвии других узлов того жу уровня
                lastRetNode = null;
            } catch (NullPointerException e) {
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            }
        }
    }

    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    private class DescendingIterator implements Iterator<E>{
        Node<E> currNode = last;
        Node<E> lastRetNode = null;
        @Override
        public boolean hasNext() {
            return (currNode != first); //вершину скрываем
        }

        @Override
        public E next() {
            if (currNode != first) {
                lastRetNode = currNode;
                currNode = currNode.prev;
                return lastRetNode.element;
            }
            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (lastRetNode == null) //реагируем на попытку повторного в текущей итерации удаление
                throw new IllegalStateException();
            if (last == first)
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            try {
                myTreeList.this.remove(lastRetNode);
                lastRetNode = null;
            } catch (NullPointerException e) {
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            }

        }

    }

    //ListIterator

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr();
    }
    private class ListItr implements ListIterator<E> {
        Node<E> currNode = first.next;
        Node<E> lastRetNode = null;

        @Override
        public boolean hasNext() {
            return (currNode != null);
        }

        @Override
        public E next() {
            if ((lastRetNode != null)&&(lastRetNode.prev == currNode)){ //если до этого двигались назад - меняем направление
                currNode = lastRetNode.next;
            }
            if (currNode != null) {
                lastRetNode = currNode;
                currNode = currNode.next;
                return lastRetNode.element;
            }
            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public boolean hasPrevious() {
            return (currNode != lastRetNode) //если пустой список
                    && (currNode != first); //вершину скрываем
        }

        @Override
        public E previous() {
            if (currNode == lastRetNode) throw new NoSuchElementException();
            if ((currNode==null)||(lastRetNode == currNode.prev)){ //если до этого двигались вперед - меняем направление
                currNode = lastRetNode.prev;
            }
            if (currNode != first) {
                lastRetNode = currNode;
                currNode = currNode.prev;
                return lastRetNode.element;
            }
            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("Index access is forbidden");
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("Index access is forbidden");
        }

        @Override
        public void remove() {
            if (lastRetNode == null) //реагируем на попытку повторного в текущей итерации удаление
                throw new IllegalStateException();
            if (last == first)
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            try {
                myTreeList.this.remove(lastRetNode);
                lastRetNode = null;
            } catch (NullPointerException e) {
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            }
        }

        @Override
        public void set(E e) {
            lastRetNode.element = e;
        }

        @Override
        public void add(E e) {
            if (currNode == null){
                linkLast(e);
                currNode = last;
            }
            else {
                linkBefore(e);
            }
            lastRetNode = null;
        }
    }


    //заглушки
    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException("Function replaceAll() is not supported");
    }

    @Override
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException("Function sort() is not supported");
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException("Function removeIf() is not supported");
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }





    //это для тестирования - убрать
    public List<List<E>> getTree(){
        List<List<E>> result = new ArrayList<>();
        Node<E> parent = first;
        result.add(new ArrayList<E>());
        for (Node<E> node = first.next; node != null; node = node.next){
            if ( ! node.parent.isSameLevel(parent) ){
                parent = node.parent;
                result.add(new ArrayList<E>());
            }
            result.get(result.size()-1).add(node.element);
        }
        return result;
    }

}

