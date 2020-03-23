package kits.bioinfo.genomerearrangement;

import java.util.Arrays;
import java.util.Random;

import kits.bioinfo.util.FrequencyMap;
import kits.bioinfo.util.Pair;

public class SyntenyBlockSizesSimulation {

    private static Random random = new Random();
    
    public static void main(String[] argc){
        
        final int genomeLength = 1_000_000;
        final int rearrangementSteps = 10_000; 
        
        int[] genome = createGenome(genomeLength);
        
        for(int i=0;i<rearrangementSteps;i++){
            doOneRearrangement(genome);
        }
        
        FrequencyMap<Integer> frequencyMap = createSyntenyBlockSizesFrequencyMap(genome);
        System.out.println(frequencyMap);
        
    }
    
    private static void doOneRearrangement(int[] genome){
        Pair<Integer> breakPointPair = generateRandomBreakPointPair(genome.length);
        int breakPoint1 = breakPointPair.first;
        int breakPoint2 = breakPointPair.second;
        
        int[] range = Arrays.copyOfRange(genome, breakPoint1, breakPoint2);
        for(int i=0;i<range.length;i++){
            genome[breakPoint2 - i - 1] = range[i];
        }
    }
    
    private static Pair<Integer> generateRandomBreakPointPair(int genomeLength){
        int breakPoint1 = random.nextInt(genomeLength);
        int breakPoint2 = random.nextInt(genomeLength);
        return breakPoint1 < breakPoint2 ? new Pair<>(breakPoint1, breakPoint2) : new Pair<>(breakPoint2, breakPoint1);  
    }
    
    private static int[] createGenome(int n){
        int[] genome = new int[n];
        for(int i=0;i<n;i++){
            genome[i] = i;
        }
        return genome;
    }
    
    private static FrequencyMap<Integer> createSyntenyBlockSizesFrequencyMap(int[] genome){
        FrequencyMap<Integer> frequencyMap = new FrequencyMap<>();
        int size = 1;
        boolean inPositiveSteam = false;
        boolean inNegativeSteam = false;
        for(int i=1;i<genome.length;i++){
            int current = genome[i];
            int previous = genome[i-1];
            if(current > previous + 1 || current < previous - 1 || current == previous + 1 && inNegativeSteam || current == previous -1 && inPositiveSteam){
                frequencyMap.put(size);
                size = 1;
                if(current == previous + 1) {
                    inPositiveSteam = true;
                    inNegativeSteam = false;
                    size = 2;
                }
                if(current == previous - 1) {
                    inPositiveSteam = false;
                    inNegativeSteam = true;
                    size = 2;
                }
            } else {
                size++;
            }
        }
        
        return frequencyMap;
    }
    
}
