package snapMain.model.helper;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ListHelper {

    //Return the values in both lists
    public static <T> List<T> getCommonValues(List<T> list1, List<T> list2)
    {
        return list1.stream().filter(list2::contains).collect(toList());
    }

    //Return the values only in the first list
    public static <T> List<T> getNewValues(List<T> newList, List<T> oldList)
    {
        return newList.stream()
                .filter(f -> !oldList.contains(f))
                .collect(toList());
    }

}
