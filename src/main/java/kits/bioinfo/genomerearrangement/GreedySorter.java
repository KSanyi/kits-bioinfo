package kits.bioinfo.genomerearrangement;

import java.util.LinkedList;
import java.util.List;

public class GreedySorter {

    public static List<Permutation> sort(Permutation originalPermutation){
        
        List<Permutation> permutations = new LinkedList<>();
        Permutation permutation = originalPermutation;
        for(int i=0;i<permutation.size();i++){
            if(!permutation.isKSorted(i)){
                permutation = permutation.doReversal(i, permutation.indexOf(i+1)+1);
                permutations.add(permutation);
            }
            if(permutation.isKReversed(i)){
                permutation = permutation.doReversal(i, i+1);
                permutations.add(permutation);
            }
            
        }
        return permutations;
    }
    
}
