import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the sorted array: ");
        int n = scanner.nextInt();
        int[] sortedArray = new int[n];

        System.out.println("Enter the sorted array elements:");
        for (int i = 0; i < n; i++) {
            sortedArray[i] = scanner.nextInt();
        }

        System.out.print("Enter the element to search for: ");
        int searchKey = scanner.nextInt();

        int result = binarySearch(sortedArray, searchKey);

        if (result != -1) {
            System.out.println("Element found at index " + result);
        } else {
            System.out.println("Element not found in the array.");
        }

        scanner.close();
    }

    static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;  
            } else if (arr[mid] < target) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }

        return -1;
    }
}
