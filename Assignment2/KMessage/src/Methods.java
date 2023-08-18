import implementations.Queue;
import implementations.Stack;

import java.io.*;
import java.text.*;
import java.util.*;

public class Methods {
  public static Queue<String> queue;
  public static Stack<String> stack;

  public static void setQueue(Queue<String> q) {
    queue = q;
  }

  public static void setStack(Stack<String> s) {
    stack = s;
  }

  public static void Menu() {
    System.out.println("----------------------------------------");
    System.out.println("|1| Import the conversation from a file|");
    System.out.println("|2| Leave the messages                 |");
    System.out.println("|3| Retrieve the newest message        |");
    System.out.println("|4| Retrieve all conversations         |");
    System.out.println("|5| Export all conversations to a file |");
    System.out.println("|6| Delete the newest message          |");
    System.out.println("|7| Statistics                         |");
    System.out.println("|8| Exit                               |");
    System.out.println("----------------------------------------");
    System.out.print(" => Enter your choice: ");
  }

  public static String[] LeaveMessage(BufferedReader reader) throws IOException {
    boolean validMessage = false;
    String[] message = null;

    while (!validMessage) {
      try {
        String input = reader.readLine();
        if (input.trim().isEmpty()) {
          throw new Exception("Empty message");
        }
        if (input.length() >= 250) {
          throw new Exception("Message exceeds 250 characters");
        }

        message = input.split("//");
        validMessage = true;

        for (String m : message) {
          if (m.trim().isEmpty()) {
            System.out.println(" Warning: Empty message ignored.");
            validMessage = false;
            break;
          }
        }
      } catch (Exception e) {
        System.out.println(" Warning: " + e.getMessage() + " and will be truncated.");
      }
    }
    return message;
  }

  public static void Transfer(String[] message, Queue<String> queue) {
    SimpleDateFormat formatter = new SimpleDateFormat("[MM/dd/yyyy HH:mm:ss]");
    Date date = new Date();
    String timestamp = formatter.format(date);

    int i = 0;
    while (i < message.length) {
      queue.enqueue(timestamp + " " + message[i]);
      i++;
    }
    System.out.println("    Message transferred to queue.");
  }

  public static void Process(Queue<String> queue, Stack<String> stack) {
    String character;
    while (!queue.isEmpty()) {
      character = queue.peek();
      stack.push(character);
      queue.dequeue();
    }
  }

  public static void PrintNewestMessage(Stack<String> stack) {
    try {
      if (stack.isEmpty()) {
        throw new Exception("No newest message found.");
      } else {
        String message = stack.peek();
        System.out.println("    The newest message: " + message);
      }
    } catch (Exception e) {
      System.out.println("    Warning: " + e.getMessage());
    }
  }

  public static void PrintAllMessages(Stack<String> stack) {
    try {
      if (stack.size() == 0) {
        throw new Exception("No messages found.");
      } else {
        System.out.println("    All messages received:");
        for (String message : stack) {
          System.out.println("    " + message);
        }
      }
    } catch (Exception e) {
      System.out.println("    Warning: " + e.getMessage());
    }
  }

  private static boolean isImported = false;

  public static void ImportMessages(Stack<String> stack) {
    if (isImported) {
      System.out.println("    Warning: File already imported.");
      return;
    }

    try {
      File file = new File("src/conversation/history2.txt");
      Scanner scanner = new Scanner(file);
      // Loop over each line in the file
      while (scanner.hasNextLine()) {
        String message = scanner.nextLine();
        stack.push(message);
      }
      // Close the Scanner object to flush any remaining data and release any system
      // resources
      scanner.close();
      System.out.println("All messages imported from file: " + file.getAbsolutePath());
      isImported = true; // set to true after successful import
    } catch (FileNotFoundException e) {
      System.out.println("    Warning: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("    Warning: " + e.getMessage());
    }
  }

  public static void ExportAllMessages(Stack<String> stack) {
    try {
      File file = new File("src/conversation/history2.txt");
      FileWriter writer = new FileWriter(file);
      // Loop over each message in the stack and write it to the file
      for (String message : stack) {
        writer.write(message + System.lineSeparator());
      }
      // Close the FileWriter object to flush any remaining data and release any
      // system resources
      writer.close();
      System.out.println("All messages exported to file: " + file.getAbsolutePath());
    } catch (Exception e) {
      System.out.println("    Warning: " + e.getMessage());
    }
  }

  public static void DeleteNewestMessage(Stack<String> stack) {
    try {
      if (stack.size() == 0) {
        throw new Exception("No messages left.");
      } else {
        stack.pop();
        System.out.println("    Newest message deleted.");
      }
    } catch (Exception e) {
      System.out.println("    Warning: " + e.getMessage());
    }
  }

  public static void Statistics(Stack<String> stack) {
    try {
      if (stack.size() == 0) {
        throw new Exception("No messages found.");
      } else {
        System.out.println("    Number of conversations received: " + stack.size());
        int totalLength = 0;
        for (String message : stack) {
          totalLength += message.length();
        }
        double averageLength = (double) totalLength / stack.size();
        long roundedAverageLength = Math.round(averageLength);
        System.out.println("    Average length of messages: " + roundedAverageLength);
      }
    } catch (Exception e) {
      System.out.println("    Warning: " + e.getMessage());
    }
  }
}