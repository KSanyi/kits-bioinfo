package kits.bioinfo;

import java.util.List;

public class TestUtil {

	public static <T> boolean equalsInAnyOrder(List<T> list1, List<T> list2) {
		return  list1.size() == list2.size() &&
				list1.containsAll(list2) &&
				list2.containsAll(list1);
	}
	
}
