
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Put comma separated values:");
        String line = scanner.nextLine();

        if (line.trim().isEmpty()) {
            System.out.println("No values provided.");
            return;
        }

        System.out.println("Enter a searched value:");
        Integer searchedValue;
        try {
            searchedValue = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid searched value.");
            return;
        }

        List<Integer> sortedList;
        try {
            sortedList = Arrays.stream(line.split(","))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input values.");
            return;
        }

        boolean found = search(sortedList, searchedValue);
        System.out.println("Value " + (found ? "found" : "not found") + " in the list.");
    }

    public static boolean search(List<Integer> sortedList, Integer value) {
        if (sortedList.isEmpty()) {
            return false;
        }

        if (sortedList.size() == 1) {
            return sortedList.get(0).equals(value);
        }

        if (value < sortedList.get(0) || value > sortedList.get(sortedList.size() - 1)) {
            return false;
        }

        List<List<Integer>> subSets = split(sortedList);
        List<Integer> leftList = subSets.get(0);
        List<Integer> rightList = subSets.get(1);
        Integer leftTop = leftList.get(leftList.size() - 1);
        Integer rightBottom = rightList.get(0);

        if (leftTop.equals(value) || rightBottom.equals(value)) {
            return true;
        }

        if (value < leftTop) {
            return search(leftList, value);
        } else {
            return search(rightList, value);
        }
    }

    private static List<List<Integer>> split(List<Integer> list) {
        int mid = list.size() / 2;
        List<Integer> left = new ArrayList<>(list.subList(0, mid));
        List<Integer> right = new ArrayList<>(list.subList(mid, list.size()));
        return List.of(left, right);
    }
}