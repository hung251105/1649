public class Main {
    public class ConstantTimeAlgorithm {
        public static void main(String[] args) {
            int[] arr = { 1, 2, 3, 4, 5 };
            int firstElement = constantTimeAlgorithm(arr);
            System.out.println("The first element is: " + firstElement);
        }

        public static int constantTimeAlgorithm(int[] arr) {
            return arr[0];
        }
    }

    public class LinearTimeAlgorithm {
        public static void main(String[] args) {
            int[] arr = { 1, 2, 3, 4, 5 };
            linearTimeAlgorithm(arr);
        }

        public static void linearTimeAlgorithm(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }
    }

    public class BinarySearch {
        public static void main(String[] args) {
            int[] arr = { 1, 3, 5, 7, 9 };
            int target = 7;
            int index = binarySearch(arr, target);
            if (index == -1) {
                System.out.println("The target element is not found in the array.");
            } else {
                System.out.println("The target element is found at index " + index + ".");
            }
        }

        public static int binarySearch(int[] arr, int target) {
            int low = 0;
            int high = arr.length - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr[mid] == target) {
                    return mid;
                } else if (arr[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return -1;
        }
    }

    public class PolynomialTimeAlgorithm {
        public static void main(String[] args) {
            int[] arr = { 1, 2, 3, 4, 5 };
            polynomialTimeAlgorithm(arr);
        }

        public static void polynomialTimeAlgorithm(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[i] * arr[j]);
                }
            }
        }
    }
}