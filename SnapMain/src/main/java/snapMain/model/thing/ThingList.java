package snapMain.model.thing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ThingList<T extends BaseObject> implements Iterable<T> {

    List<T> things;

    protected ThingList(List<T> t) {
        things = new ArrayList<>();
        things.addAll(t);
    }

    ThingList(ThingList<T> t)
    {
        things = new ArrayList<>();
        things.addAll(t.getThings());
    }

    public abstract void sort();

    public abstract void setSortMode(String m);

    public List<T> getThings()
    {
        return things;
    }

    public void addAll(List<T> toAdd) {
        things.addAll(toAdd);
    }

    public List<T> subList(int i, int fromIndex) {
        return things.subList(i, fromIndex);
    }

    protected void clear() {
        things.clear();
    }

    public void removeAll(List<T> list) {
        things.removeAll(list);
    }

    public int size() {
        return things.size();
    }

    public T get(int i) {
        return things.get(i);
    }

    public void add(T t) {
        things.add(t);
    }

    public boolean contains(T t) {
        return things.contains(t);
    }
    public Iterator<T> iterator() {
        return getThings().listIterator();
    }

    public boolean isEmpty() {
        return things.isEmpty();
    }

    public void remove(T t) {
        things.remove(t);
    }

    public void replace(T thing) {
        if(this.contains(thing))
            things.set(things.indexOf(thing), thing);
    }

    public int indexOf(T t)
    {
        return things.indexOf(t);
    }

    public void shuffle() {
        Collections.shuffle(things);
    }
}
