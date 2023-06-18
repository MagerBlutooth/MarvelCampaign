package model.helper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ListHelper {

    public static <T> List<T> getCommonValues(List<T> list1, List<T> list2)
    {
        List<T> commonList = list1.stream().filter(list2::contains).collect(toList());
        return commonList;
    }

}
