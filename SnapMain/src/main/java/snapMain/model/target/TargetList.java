package snapMain.model.target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class TargetList<T extends SnapTarget> implements Iterable<T>, Cloneable {

    List<T> things;

    protected TargetList(List<T> t) {
        things = new ArrayList<>();
        things.addAll(t);
    }

    TargetList(TargetList<T> t)
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

    public List<T> subList(int fromIndex, int toIndex) {
        return things.subList(fromIndex, toIndex);
    }

    public void clear() {
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
    public T get(T t)
    {
        for(T thing: things)
        {
            if(thing.equals(t))
                return thing;
        }
        return null;
    }

    public boolean add(T t) {
        return things.add(t);
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

    public boolean remove(T t) {
        return things.remove(t);
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

    public T getRandom() {
        List<T> randomList = new ArrayList<>();
        randomList.addAll(this.getThings());
        Collections.shuffle(randomList);
        return randomList.get(0);
    }

    public TargetList<T> cloneNewCopy() {
        try {
            return (TargetList<T>) this.clone();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
