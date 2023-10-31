package snapMain.model.helper;

import snapMain.model.target.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Randomizer {

    public <V extends Object> V getRandomElement(List<V> elements)
    {
      Random random = new Random();
      int randomVal = random.nextInt(elements.size());
      return elements.get(randomVal);
    }

    public List<Card> getRandomElements(List<Card> elements, int size) {
        List<Integer> nums = new ArrayList<>();
        for(int i = 0; i < elements.size(); i++)
            nums.add(i);
        Collections.shuffle(nums);
        List<Card> randomCards = new ArrayList<>();
        for(int i = 0; i < size; i++)
            randomCards.add(elements.get(nums.get(i)));
        return randomCards;
    }
}
