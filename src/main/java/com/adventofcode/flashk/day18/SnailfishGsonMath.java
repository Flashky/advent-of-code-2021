package com.adventofcode.flashk.day18;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.adventofcode.flashk.common.JsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public final class SnailfishGsonMath {

	private static final String OPEN_BRACKET = "[";
	private static final String CLOSE_BRACKET = "]";
    private static final String SEPARATOR = ",";

    public SnailfishGsonMath() {}

    public static String sum(String a , String b) {
		return new StringBuilder()
				.append(OPEN_BRACKET)
				.append(a)
				.append(SEPARATOR)
				.append(b)
				.append(CLOSE_BRACKET)
				.toString();	
    }

    public static String reduce(String number) {
        Optional<JsonElement> result = reduce(JsonUtil.buildGsonTree(number));

        if(result.isPresent()) {
            return result.get().getAsString();
        }

        return number;
    }

    public static String explode(String number) {
        Optional<JsonElement> result = explode(JsonUtil.buildGsonTree(number));

        if(result.isPresent()) {
            return result.get().toString();
        }

        return number;
    }
    
    public static String split(String number) {

        Optional<JsonElement> result = split(JsonUtil.buildGsonTree(number));
        
        if(result.isPresent()) {
            return result.get().toString();
        }

        return number;
    }
    
    /**
     * Calculates the magnitude of a snailfish number.
     * <p>The magnitude of a pair is 3 times the magnitude of its left element plus 2 times the magnitude of its right element.</p>
     * <p>The magnitude of a regular number is just that number.</p>
     * @param number the snailfish number to calculate the magnitude from.
     * @return A long indicating the magnitude of the snailfish number.
     */
    public static long magnitude(String number) {
        return magnitude(JsonUtil.buildGsonTree(number));
    }
    
    private static Optional<JsonElement> reduce(JsonElement number) {

        Optional<JsonElement> explodedNumber = explode(number);

        if(explodedNumber.isPresent()) {
            return reduce(explodedNumber.get());
        }

        Optional<JsonElement> splitNumber = split(number);

        if(splitNumber.isPresent()) {
            return reduce(splitNumber.get());
        }

        return Optional.empty();
        //return Optional.of(number);
    }

    /**
     * Attempts to explode a snailfish number.
     * @param number the snailfish number to explode.
     * @return An {@link Optional} containing the new exploded number. If exploding does not apply, an {@link Optional#empty()} will be returned.
     */
    private static Optional<JsonElement> explode(JsonElement number) {

        // Explode left side
        Optional<JsonElement> leftNumber = explode(number, number.getAsJsonArray(), number.getAsJsonArray().get(0), 1);

        if(leftNumber.isPresent()) {
            return leftNumber;
        }

        // Explode right side
        return explode(number, number.getAsJsonArray(), number.getAsJsonArray().get(1), 1);

    }
    private static Optional<JsonElement> explode(JsonElement originalNumber, JsonArray parentNumber, JsonElement number, int depth) {

        if(number.isJsonPrimitive()) {
            return Optional.empty();
        }

        // If it is an array, then it is an snailfish number: [x,y]
        JsonArray snailfishNumber = number.getAsJsonArray();

        // Si estamos ante una pareja de profundidad de 4 o mayor
        if (isPair(snailfishNumber) && depth >= 4) {

            // Idea: preorder traversal
            List<JsonElement> preorderTree = preorder(originalNumber);

            int explodedNumberIndex = 0;
            for(JsonElement node : preorderTree) {
                if(node == number) {
                    break;
                }
                explodedNumberIndex++;
            }

            addExplodedNumbers(preorderTree, explodedNumberIndex);
            explodePair(parentNumber, snailfishNumber);

            return Optional.of(originalNumber);

        }

        // Explode left side
        Optional<JsonElement> leftNumber = explode(originalNumber, snailfishNumber, snailfishNumber.get(0), depth + 1);

        if (leftNumber.isPresent()) {
            return leftNumber;
        }

        // Explode right side
        return explode(originalNumber, snailfishNumber, snailfishNumber.get(1), depth+1);

    }

    private static void addExplodedNumbers(List<JsonElement> preorderTree, int explodedNumberIndex) {

        // Values to sum
        int leftNumber = preorderTree.get(explodedNumberIndex+1).getAsInt();
        int rightNumber = preorderTree.get(explodedNumberIndex+2).getAsInt();

        // Left side - Find the first right-most number if any and update it
        List<JsonElement> filteredNumberNodes = preorderTree.stream().limit(explodedNumberIndex).filter(JsonUtil::isInt).collect(Collectors.toList());
        if(!filteredNumberNodes.isEmpty()) {
            JsonElement lastNumber = filteredNumberNodes.get(filteredNumberNodes.size() - 1);
            Optional<JsonArray> lastParentNumber = preorderTree.stream()
                    .filter(JsonElement::isJsonArray)
                    .map(JsonElement::getAsJsonArray)
                    .filter(node -> node.get(0) == lastNumber || node.get(1) == lastNumber).findFirst();

            if(lastParentNumber.isPresent()) {
                JsonArray updateableParent = lastParentNumber.get();
                int newValue = lastNumber.getAsInt() + leftNumber;

                if(lastParentNumber.get().get(0) == lastNumber) {
                    updateableParent.set(0, JsonUtil.buildGsonTree(String.valueOf(newValue)));
                } else {
                    updateableParent.set(1, JsonUtil.buildGsonTree(String.valueOf(newValue)));
                }
            }
        }

        // Right side - Find the first left-most number if any and update it
        Optional<JsonElement> rightNumberNode = preorderTree.stream().skip(explodedNumberIndex+3).filter(JsonElement::isJsonPrimitive).findFirst();
        if(rightNumberNode.isPresent()) {

            JsonElement lastNumber = rightNumberNode.get();
            List<JsonArray> lastParentNumbers = preorderTree.stream()
                    .filter(JsonElement::isJsonArray)
                    .map(JsonElement::getAsJsonArray)
                    .filter(node -> node.get(0) == lastNumber || node.get(1) == lastNumber).collect(Collectors.toList());

            if(!lastParentNumbers.isEmpty()) {
                JsonArray updateableParent = lastParentNumbers.get(lastParentNumbers.size()-1);
                int newValue = lastNumber.getAsInt() + rightNumber;
                if(updateableParent.get(0) == lastNumber) {
                    updateableParent.set(0, JsonUtil.buildGsonTree(String.valueOf(newValue)));
                } else {
                    updateableParent.set(1, JsonUtil.buildGsonTree(String.valueOf(newValue)));
                }
            }
        }

    }


    private static List<JsonElement> preorder(JsonElement originalNumber) {
        // https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
        List<JsonElement> preorderTree = new ArrayList<>();
        preorder(originalNumber, preorderTree);

        return preorderTree;
    }

    private static void preorder(JsonElement jsonNode, List<JsonElement> preorderTree) {

        if(jsonNode != null) {

            preorderTree.add(jsonNode);

            if(jsonNode.isJsonArray()) {
                preorder(jsonNode.getAsJsonArray().get(0), preorderTree);
                preorder(jsonNode.getAsJsonArray().get(1), preorderTree);
            }
        }
    }

    private static void explodePair(JsonElement parentNumber, JsonElement pairNumber) {
        JsonElement updatedNumber = JsonUtil.buildGsonTree("0");

        // Pair is left child
        if (parentNumber.getAsJsonArray().get(0) == pairNumber) {
            parentNumber.getAsJsonArray().set(0, updatedNumber);
        } else {
            parentNumber.getAsJsonArray().set(1, updatedNumber);
        }

    }
    private static boolean isPair(JsonElement number) {
        return number != null &&
                number.isJsonArray()&&
                JsonUtil.isInt(number.getAsJsonArray().get(0)) &&
                JsonUtil.isInt(number.getAsJsonArray().get(1));
    }

    /**
     * Attempts to split a snailfish number.
     * @param number the snailfish number to split.
     * @return An {@link Optional} containing the new split number. If splitting does not apply, an {@link Optional#empty()} will be returned.
     */
    private static Optional<JsonElement> split(JsonElement number) {

        if(number.isJsonPrimitive()) {
            return performSplit(number);
        }

        // Attempt to split left child
        JsonArray numberArray = number.getAsJsonArray();

        JsonElement leftNumber = numberArray.get(0);
        Optional<JsonElement> splitLeft = split(leftNumber);
        if(splitLeft.isPresent()) {
            JsonArray updatedNumber = number.getAsJsonArray();
            updatedNumber.set(0, splitLeft.get());
            return Optional.of(updatedNumber);
        }

        // Attempt to split right child
        JsonElement rightNumber = numberArray.get(1);
        Optional<JsonElement> splitRight = split(rightNumber);
        if(splitRight.isPresent()) {
            JsonArray updatedNumber = number.getAsJsonArray();
            updatedNumber.set(1, splitRight.get());
            return Optional.of(updatedNumber);
        }

        return Optional.empty();

    }

    /**
     * Attempts to split a snailfish number.
     * <p>Only non-pairs are allowed to be split on this method.</p>
     * @param number the snailfish number to split.
     * @return An {@link Optional} containing the new split number. If splitting does not apply, an {@link Optional#empty()} will be returned.
     * @throws IllegalArgumentException on attempting to split pair.
     */
    private static Optional<JsonElement> performSplit(JsonElement number) {

        if(number.isJsonArray()) {
            throw new IllegalArgumentException("Cannot split a pair.");
        }

        float value = (float) number.getAsInt();
        if(value < 10) {
            return Optional.empty();
        }

        float division = value / 2;
        int leftNumber = (int) Math.floor(division);
        int rightNumber = (int) Math.ceil(division);

        JsonArray array = new JsonArray();
        array.add(leftNumber);
        array.add(rightNumber);

        return Optional.of(array);
    }

    /**
     * Calculates the magnitude of a snailfish number.
     * <p>The magnitude of a pair is 3 times the magnitude of its left element plus 2 times the magnitude of its right element.</p>
     * <p>The magnitude of a regular number is just that number.</p>
     * @param number the snailfish number as a JsonNode tree to calculate the magnitude from.
     * @return A long indicating the magnitude of the snailfish number.
     */
    private static long magnitude(JsonElement number) {

        if(JsonUtil.isInt(number)) {
            return number.getAsInt();
        }

        return 3 * magnitude(number.getAsJsonArray().get(0)) + 2 * magnitude(number.getAsJsonArray().get(1));
    }

}
