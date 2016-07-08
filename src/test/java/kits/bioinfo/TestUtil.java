package kits.bioinfo;

import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class TestUtil {

	public static <T> boolean equalsInAnyOrder(Collection<T> list1, Collection<T> list2) {
		return  list1.size() == list2.size() &&
				list1.containsAll(list2) &&
				list2.containsAll(list1);
	}
	
	public static class EqualsInAnyOrder<T> extends BaseMatcher<Collection<T>> {

		private final Collection<T> collection;
		
		public EqualsInAnyOrder(Collection<T> collection){
			this.collection = collection;
		}
		
		@SafeVarargs
		public EqualsInAnyOrder(T ... items){
			this.collection = Arrays.asList(items);
		}
		
		@Override
		public boolean matches(Object item) {
			@SuppressWarnings("unchecked")
			Collection<T> otherCollection = (Collection<T>)item;
			
			return  collection.size() == otherCollection.size() &&
					collection.containsAll(otherCollection) &&
					otherCollection.containsAll(collection);
		}

		@Override
		public void describeTo(Description description) {
			description.appendText(collection + " in any order");
		}
	}
	
}
