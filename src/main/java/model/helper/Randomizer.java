package model.helper;

import java.util.List;
import java.util.Random;

public class Randomizer {

    public <V extends Object> V getRandomElement(List<V> elements)
    {
      Random random = new Random();
      int randomVal = random.nextInt(elements.size());
      return elements.get(randomVal);
    }
}
