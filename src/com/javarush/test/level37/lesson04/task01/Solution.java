package com.javarush.test.level37.lesson04.task01;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
/* Круговой итератор
Класс Solution наследуется от ArrayList.
        Напишите свой класс RoundIterator внутри Solution, который будет итератором для списка Solution.
        Итератор должен ходить по кругу по всем элементам.
        В остальном поведение должно быть идентичным текущему итератору.
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        //list.add(2);
        //list.add(3);
        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }

        System.out.println();
        count = 0;
        ListIterator<Integer> iter = list.listIterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
            //iter.remove();
            //if (count==1) iter.remove();
            //if (count==2) iter.add(4);
            count++;
            if (count == 10) {
                break;
            }
        }

        System.out.println();
        count = 0;
        iter = list.listIterator(0);
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
            //iter.remove();
            //if (count==1) iter.remove();
            //if (count==2) iter.add(4);
            count++;
            if (count == 10) {
                break;
            }
        }

    }
    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }
    @Override
    public ListIterator<T> listIterator() {
        return new RoundIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new RoundIterator(index);
    }

    public class RoundIterator implements ListIterator<T> {
        private ListIterator<T> iterator;
        private int c = 0;

        public RoundIterator() {
            this.iterator = Solution.super.listIterator();
        }
        public RoundIterator(int index) {
            this.iterator = Solution.super.listIterator(index);
        }

        @Override
        public boolean hasNext() {
            //if (c==10000) return iterator.hasNext();
            return (Solution.this.size() > 0);
        }
        @Override
        public T next() {
            //if (c==10000) return iterator.next();
            if (!iterator.hasNext()) {iterator = Solution.super.listIterator(); c++;};
            return iterator.next();
        }
        @Override
        public void remove() {
            iterator.remove();
        }
        @Override
        public boolean hasPrevious() {
            //if (c==10000) return iterator.hasPrevious();
            return (Solution.this.size() > 0);
        }
        @Override
        public T previous() {
            //if (c==10000) return iterator.previous();
            if (!iterator.hasPrevious()) {iterator = Solution.super.listIterator(Solution.this.size()); c++;}
            return iterator.previous();
        }
        @Override
        public int nextIndex() {
            return iterator.nextIndex();
        }
        @Override
        public int previousIndex() {
            return iterator.previousIndex();
        }
        @Override
        public void set(T t) {
            iterator.set(t);
        }
        @Override
        public void add(T t) {
            iterator.add(t);
        }
    }
}
