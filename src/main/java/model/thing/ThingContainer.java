package model.thing;

import java.util.function.Supplier;

public class ThingContainer<T extends Thing> {
    private Supplier<T> supplier;

    public ThingContainer(Supplier<T> supplier)
    {
        this.supplier = supplier;
    }

    public T createContents()
    {
        return supplier.get();
    }

}
