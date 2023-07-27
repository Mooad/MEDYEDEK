import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SoManyPermutations {

    public List<String> singlePermutations(String permutation) {
        char[] charsOfPermutations = permutation.toCharArray();
        Set<String> letters = new HashSet<>();
        List<String> results = new ArrayList<>();
        results.add(permutation);

        BigInteger numberOfPossibilities;
        BigInteger numberOfCombinations = BigInteger.ONE;

        // Getting unique letters without duplicates
        for (char letter : charsOfPermutations) {
            letters.add(Character.toString(letter));
        }

        numberOfPossibilities = calculateCombinations(permutation);
        String combinaisonGenerated = results.get(results.size() - 1);

        while (numberOfCombinations.compareTo(numberOfPossibilities) < 0) {
            combinaisonGenerated = getNextPossibleCombinaison(combinaisonGenerated, results);

            if (combinaisonGenerated != null && !results.contains(combinaisonGenerated)) {
                results.add(combinaisonGenerated);
                numberOfCombinations = numberOfCombinations.add(BigInteger.ONE);
            }
        }

        return results;
    }

    public static BigInteger calculateCombinations(String word) {
        int[] letterCounts = new int[26];

        // Count the occurrences of each distinct letter
        for (char c : word.toCharArray()) {
            letterCounts[c - 'a']++;
        }

        BigInteger numerator = factorial(word.length());
        BigInteger denominator = BigInteger.ONE;

        // Calculate the product of factorials of letter counts
        for (int count : letterCounts) {
            denominator = denominator.multiply(factorial(count));
        }

        return numerator.divide(denominator);
    }

    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public String getNextPossibleCombinaison(String alreadyExistingCombin, List<String> results) {
        StringBuilder currentCombinaison = moveByOneIndexFrom1 (alreadyExistingCombin).reverse();

        if (!results.contains(currentCombinaison.toString())) {
            return currentCombinaison.toString();
        } else {
            if (!results.contains(currentCombinaison.reverse().toString())) {
                return currentCombinaison.toString();
            } else {
                String newCurrentCombinaison = switchSecondPartOfTheString(currentCombinaison.toString(), results);
                    return newCurrentCombinaison;

            }
        }
    }

    public String switchSecondPartOfTheString(String alreadyExistingCombin, List<String> results) {
        StringBuilder currentCombinaison = new StringBuilder(alreadyExistingCombin);
        String secondPart = currentCombinaison.substring(alreadyExistingCombin.length() / 2);
        secondPart = new StringBuilder(secondPart).reverse().toString();
        if (secondPart.contentEquals(new StringBuilder(secondPart).reverse())) {
            currentCombinaison = moveByOneIndexFrom1(currentCombinaison.toString());
            secondPart = String.valueOf(moveByOneIndexFrom1(new StringBuilder(currentCombinaison.substring(alreadyExistingCombin.length() / 2)).reverse().toString()));
        }
        currentCombinaison.replace(alreadyExistingCombin.length() / 2, alreadyExistingCombin.length(), secondPart);

        if (!results.contains(currentCombinaison.toString())) {
            return currentCombinaison.toString();
        }
        return currentCombinaison.reverse().toString();
    }

    public StringBuilder moveByOneIndexFrom1(String alreadyExistingCombin) {
        StringBuilder currentCombinaison = new StringBuilder();

        currentCombinaison.append(alreadyExistingCombin.substring(1));
        currentCombinaison.append(alreadyExistingCombin.charAt(0));

        return currentCombinaison;
    }
}
