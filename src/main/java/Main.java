
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Put comma separated values");
        String line = scanner.nextLine();
        System.out.println("Enter a searched value");
        Integer searchedValue = Integer.parseInt(scanner.nextLine());
        List<String> split = Arrays.asList(line.split(","));
        System.out.println(search(split.stream().map(Integer::parseInt).sorted().collect(Collectors.toList()), searchedValue));
    }

    public static boolean search(List<Integer> sortedList, Integer value) {
        if (sortedList.size() > 1) {
            if (value < sortedList.get(0) || value > sortedList.get(sortedList.size() - 1)) {
                return false;
            }
            List<List<Integer>> subSets = split(sortedList);
            List<Integer> leftList = subSets.get(0);
            List<Integer> rightList = subSets.get(1);
            Integer leftTop = leftList.get(leftList.size() - 1);
            Integer rightBottom = rightList.get(0);
            if (leftTop.equals(value)) return true;
            if (rightBottom.equals(value)) return true;
            if (value < leftTop) {
                return search(leftList, value);
            } else if (value > rightBottom) {
                return search(rightList, value);
            } else return false;
        }
        return sortedList.get(0).equals(value);
    }

    private static List<List<Integer>> split(List<Integer> list) {
        List<Integer> left = new LinkedList<>();
        List<Integer> right = new LinkedList<>();
        for (int i = 0; i <= list.size() - 1; i++) {
            if (i < list.size() / 2) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        return List.of(left, right);
    }
}