package com.javarush.test.level20.lesson10.bonus04;

import java.io.*;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
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
public class Solution
        extends AbstractList<String>
        implements List<String>, Cloneable, Serializable
{

    private static final long serialVersionUID = 0L;

    public static void main(String[] args) throws Exception {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }


        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

        for (List<String> line : ((Solution)list).getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+((Solution)list).first.element);
        System.out.print(" last "+((Solution)list).last.element);
        System.out.println(" size "+((Solution)list).size);
        System.out.println("============================");
        System.out.println("deleted "+list.remove("2"));
        System.out.println("deleted "+list.remove("9"));
        System.out.println("----------------------------");
        for (List<String> line : ((Solution)list).getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+((Solution)list).first.element);
        System.out.print(" last "+((Solution)list).last.element);
        System.out.println(" size "+((Solution)list).size);
        System.out.println("============================");
        list.addAll(Arrays.asList("16", "17", "18", "19", "20"));
        for (List<String> line : ((Solution)list).getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+((Solution)list).first.element);
        System.out.print(" last "+((Solution)list).last.element);
        System.out.println(" size "+((Solution)list).size);
        System.out.println("============================");
        System.out.println(" deleted "+list.remove("18"));
        System.out.println(" deleted "+list.remove("20"));
        for (List<String> line : ((Solution)list).getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+((Solution)list).first.element);
        System.out.print(" last "+((Solution)list).last.element);
        System.out.println(" size "+((Solution)list).size);
        System.out.println("============================");
        list.addAll(Arrays.asList("21", "22"));
        for (List<String> line : ((Solution)list).getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.print(" first "+((Solution)list).first.element);
        System.out.print(" last "+((Solution)list).last.element);
        System.out.println(" size "+((Solution)list).size);
        System.out.println("============================");
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
        System.out.println(((Solution)list).getParent("1"));
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        System.out.println(((Solution)list).getParent("1"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("13"));
        System.out.println(((Solution)list).getParent("1"));
        //*******************************************
        System.out.println("***************");
        try {
            List<String> list1 = ((Solution)list).clone();
            System.out.println(" после клонирования :");
            for (List<String> line : ((Solution)list1).getTree()){
                for (String s : line){
                    System.out.print(s+" ");
                }
                System.out.println();
            }
            System.out.print(" first "+((Solution)list1).first.element);
            System.out.print(" last "+((Solution)list1).last.element);
            System.out.println(" size "+((Solution)list1).size);
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
        Solution list2 = (Solution)ois.readObject();
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
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("============================");
        for (Iterator<String> iterator = ((Solution)list).descendingIterator(); iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("============================");
        for (Iterator<String> iterator = ((Solution)list).descendingIterator(); iterator.hasNext();){
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
        //удаление через один в прямой итерации
        list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        //list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));
        System.out.println();
        for (List<String> line : ((Solution)list).getTree()){
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
        System.out.println("============================");
        //удаление через один в Обратной итерации
        list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        //list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));
        System.out.println();
        for (List<String> line : ((Solution)list).getTree()){
            for (String s : line){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        System.out.println();
        for (Iterator<String> iterator = ((Solution)list).descendingIterator(); iterator.hasNext();){
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

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

    }

    private int size = 0;
    private Node first = new Node(null, null);
    private Node last = first;

    @Override
    public int size() {
        return size;
    }

    public String getParent(String value) {
        Node node = getNode(value);
        if (node == null) return null;
        if (node == first) return null;
        return node.parent.element;
    }

    @Override
    public Solution clone() throws CloneNotSupportedException {
        Solution result = null;
        try {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(buf);
            oos.writeObject(this);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
            result = (Solution)ois.readObject();
        }
        catch (Exception e){e.printStackTrace();}

        return result;
    }


    private static class Node implements Serializable{
        private static final long serialVersionUID = 1L;
        String element;
        Node parent;
        Node prev;
        Node next;
        Node leftChild;
        Node rightChild;
        Node (String element, Node prev){
            this.element = element;
            this.prev = prev;
        }
        private boolean isSameLevel(Node otherNode){
            int level = 0;
            for (Node node = parent; node != null; node = node.parent) level++;
            int otherLevel = 0;
            for (Node node = otherNode.parent; node != null; node = node.parent) otherLevel++;
            return (level == otherLevel);
        }
    }
    public Solution(){}
    public Solution(Collection<String> c){
        this();
        addAll(c);
    }

    public String getFirst() {
        if (first == null)
            throw new NoSuchElementException();
        if (first.next == null) //вершину не показываем
            throw new NoSuchElementException();
        return first.next.element;
    }

    public String getLast() {
        if (last == null)
            throw new NoSuchElementException();
        if (last == first) //вершину не показываем
            throw new NoSuchElementException();
        return last.element;
    }

    @Override
    public boolean add(String element){
        return linkLast(element);
    }

    private boolean linkLast(String element){
        Node newNode;
        newNode = new Node(element, last);
        last.next = newNode;
        Node parent = getParentNodeForNewChild();
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

    private boolean linkBefore(String element){
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    private Node getParentNodeForNewChild(){
        Node parent = last.parent;
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

    @Override
    public boolean addAll(Collection<? extends String> c){
        Object[] a = c.toArray();
        if (a.length == 0) return false;
        for (Object o : a){
            String e = (String)o;
            add(e);
        }
        return true;
    }

    @Override
    public boolean remove(Object o){
        Node topNode = getNode(o);
        if (topNode == null) return false;
        return remove(topNode);
    }

    private boolean remove(Node topNode){
        if (topNode == null) throw new NullPointerException("deleting null-list element attempt");
        int oldSize = size;
        deleteSubTree(topNode);
        if (topNode != first) unlink(topNode); //вершину не трогаем - ее как бы нет
        return (oldSize - size) > 0 ;
    }

    public boolean removeAll(Collection<?> c){
        Object[] a = c.toArray();
        if (a.length == 0) return false;
        int oldSize = size;
        for (Object o : a){
            String e = (String)o;
            remove(e);
        }
        return (oldSize - size) > 0 ;
    }

    public void clear(){
        remove(first);
    }

    private Node getNode(Object o){
        for (Node node = first.next; node != null; node = node.next){ //начинаем не с вершину - вершину не показываем
            if ( ((o == null)&&(node.element == null))||( (o != null)&&(o.equals(node.element)) ) ) {
                return node;
            }
        }
        return null;
    }

    private void deleteSubTree(Node topNode){
        if (topNode == null) return;
        Node node;
        node = findAndDeleteLeftChild(topNode);
        if (node != null) {
            unlink(node);
        }
        node = findAndDeleteRightChild(topNode);
        if (node != null) {
            unlink(node);
        }
    }

    private Node findAndDeleteLeftChild(Node topNode){
        Node node = topNode.leftChild;
        if (node != null) deleteSubTree(node);
        return node;
    }

    private Node findAndDeleteRightChild(Node topNode){
        Node node = topNode.rightChild;
        if (node != null) deleteSubTree(node);
        return node;
    }

    private String unlink(Node node) {
        if (node == null) return null;
        String el = node.element;
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
    public Iterator<String> iterator() {
        return new Itr();
    }
    private class Itr implements Iterator<String> {
        Node currNode = first.next;
        Node lastRetNode = null;
        public boolean hasNext() {
            return (currNode != null);
        }
        public String next() {
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
                Solution.this.remove(lastRetNode);
                currNode = lastRetNode.next; //переустанавливаем currNode, т.к. при удалении под-дерева currNode может оказаться == null, хотя до удаления был != null. Такое будет, если удалить правую узел, при отсутвии других узлов того жу уровня
                lastRetNode = null;
            } catch (NullPointerException e) {
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            }
        }
    }
    public Iterator<String> descendingIterator() {
        return new DescendingIterator();
    }
    private class DescendingIterator implements Iterator<String>{
        Node currNode = last;
        Node lastRetNode = null;
        @Override
        public boolean hasNext() {
            return (currNode != first); //вершину скрываем
        }
        @Override
        public String next() {
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
                Solution.this.remove(lastRetNode);
                lastRetNode = null;
            } catch (NullPointerException e) {
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            }
        }
    }

    //ListIterator
    @Override
    public ListIterator<String> listIterator() {
        return new ListItr();
    }
    @Override
    public ListIterator<String> listIterator(int index) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }
    private class ListItr implements ListIterator<String> {
        Node currNode = first.next;
        Node lastRetNode = null;
        @Override
        public boolean hasNext() {
            return (currNode != null);
        }
        @Override
        public String next() {
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
        public String previous() {
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
        public void remove() {
            if (lastRetNode == null) //реагируем на попытку повторного в текущей итерации удаление
                throw new IllegalStateException();
            if (last == first)
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            try {
                Solution.this.remove(lastRetNode);
                lastRetNode = null;
            } catch (NullPointerException e) {
                throw new ConcurrentModificationException(); //реагируем на некое внешнее воздействие на список, токое, что привело к попытку удалить удаленный элемент
            }
        }
        @Override
        public void set(String e) {
            lastRetNode.element = e;
        }
        @Override
        public void add(String e) {
            if (currNode == null){
                linkLast(e);
                currNode = last;
            }
            else {
                linkBefore(e);
            }
            lastRetNode = null;
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("Index access is forbidden");
        }
        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("Index access is forbidden");
        }
    }

    //заглушки
    @Override
    public String get(int index) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }

    public void sort(Comparator<? super String> c) {
        throw new UnsupportedOperationException("Function sort() is not supported");
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }
    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }
    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }
    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }
    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }
    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Index access is forbidden");
    }
    //
    public List<List<String>> getTree(){
        List<List<String>> result = new ArrayList<>();
        Node parent = first;
        result.add(new ArrayList<String>());
        for (Node node = first.next; node != null; node = node.next){
            if ( ! node.parent.isSameLevel(parent) ){
                parent = node.parent;
                result.add(new ArrayList<String>());
            }
            result.get(result.size()-1).add(node.element);
        }
        return result;
    }

}

