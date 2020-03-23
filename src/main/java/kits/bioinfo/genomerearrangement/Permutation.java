package kits.bioinfo.genomerearrangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Permutation {

    public static Permutation parse(String permutationString) {
        String permutaionStringWithoutBrackets = permutationString.replace("(", "").replace(")", "");
        return new Permutation(
                Arrays.asList(permutaionStringWithoutBrackets.split(" ")).stream()
                .map(intString -> Integer.parseInt(intString)).collect(Collectors.toList()));
    }

    private final List<Integer> values;

    public Permutation(List<Integer> values) {
        this.values = Collections.unmodifiableList(values);
    }

    public Permutation(Integer... values) {
        this.values = Collections.unmodifiableList(Arrays.asList(values));
    }

    public int size() {
        return values.size();
    }
    
    public int numberOfBreakPoints(){
        int nrOfBreakPoints = 0;
        if(values.get(0) != 1) nrOfBreakPoints++;
        for(int i=1;i<size();i++){
            if(values.get(i) != values.get(i-1) + 1){
                nrOfBreakPoints++;
            }
        }
        if(values.get(size()-1) != size()) nrOfBreakPoints++;
        
        return nrOfBreakPoints;
    }

    @Override
    public String toString() {
        return values.stream().map(v -> v > 0 ? "+" + v.toString() : v.toString()).collect(Collectors.joining(" ", "(", ")"));
    }

    public Permutation doReversal(int breakPoint1, int breakPoint2) {
        List<Integer> part1 = new ArrayList<>(values.subList(0, breakPoint1));
        List<Integer> part2 = new ArrayList<>(values.subList(breakPoint1, breakPoint2));
        List<Integer> part3 = new ArrayList<>(values.subList(breakPoint2, values.size()));
        part2 = reverse(part2);
        List<Integer> newValues = new ArrayList<>();
        newValues.addAll(part1);
        newValues.addAll(part2);
        newValues.addAll(part3);
        return new Permutation(newValues);
    }

    private List<Integer> reverse(List<Integer> values) {
        List<Integer> reversedList = new ArrayList<>(values.size());
        for (int i = values.size() - 1; i >= 0; i--) {
            reversedList.add(-values.get(i));
        }
        return reversedList;
    }

    public boolean isSorted() {
        for (int i = 0; i < size(); i++) {
            if (values.get(i) != i + 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isKSorted(int k) {
        return values.get(k) == k + 1;
    }

    public boolean isKReversed(int k) {
        return values.get(k) == -(k + 1);
    }

    public int indexOf(int k) {
        int index = values.indexOf(k);
        if (index > -1) {
            return index;
        } else {
            return values.indexOf(-k);
        }
    }
    
    @Override
    public boolean equals(Object other){
        if(other == null) return false;
        return values.equals(((Permutation)other).values);
    }
    
    @Override
    public int hashCode(){
        return values.hashCode();
    }

}
