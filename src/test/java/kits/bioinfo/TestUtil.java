package kits.bioinfo;

import java.util.Collection;

public class TestUtil {

	public static <T> boolean equalsInAnyOrder(Collection<T> list1, Collection<T> list2) {
		return  list1.size() == list2.size() &&
				list1.containsAll(list2) &&
				list2.containsAll(list1);
	}
	
}
