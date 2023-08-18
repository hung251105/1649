import java.io.*;

import implementations.Queue;
import implementations.Stack;

public class Main {
    public static Queue<String> queue = new Queue<>();
    public static Stack<String> stack = new Stack<>();

    public static void main(String[] args) {
        System.out.println("----------------------------------------");
        System.out.println("    KMessage                      v5.8.0");
        Methods.Menu();
        int choice = 0;
        do {
            try {
                String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
                if (input.isEmpty()) {
                    System.out.println("Invalid choice! Please enter a number.");
                    continue;
                }
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Please enter a number.");
                continue;
            } catch (IOException e) {
                System.out.println("Invalid choice! Please enter a number.");
                continue;
            }
            switch (choice) {
                case 1:
                    Methods.ImportMessages(stack);
                    Methods.Menu();
                    break;
                case 2:
                    System.out.println("    Enter message (End with //)");
                    try {
                        String[] message = Methods.LeaveMessage(new BufferedReader(new InputStreamReader(System.in)));
                        double beginTime = System.currentTimeMillis();
                        Methods.Transfer(message, queue);
                        Methods.Process(queue, stack);
                        double endTime = System.currentTimeMillis();
                        double time = endTime - beginTime;
                        System.out.println("|*| Time: " + time + "ms");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Methods.Menu();
                    break;
                case 3:
                    Methods.PrintNewestMessage(stack);
                    Methods.Menu();
                    break;
                case 4:
                    Methods.PrintAllMessages(stack);
                    Methods.Menu();
                    break;
                case 5:
                    Methods.ExportAllMessages(stack);
                    Methods.Menu();
                    break;
                case 6:
                    Methods.DeleteNewestMessage(stack);
                    Methods.PrintAllMessages(stack);
                    Methods.Menu();
                    break;
                case 7:
                    Methods.Statistics(stack);
                    Methods.Menu();
                    break;
                case 8:
                    System.out.println("----------------------------------------");
                    System.out.println("    KMessage                      v5.8.0");
                    System.out.println("----------------------------------------");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number.");
                    break;
            }
        } while (choice != 8);
    }
}