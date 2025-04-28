import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Toolbox {

  /**
   * Finds the length of a singly linked list given the head.
   *
   * @param head the head node of the singly linked list
   * @return the number of nodes in the list
   * @throws IllegalArgumentException if the head is null
   */
  public static int length(SingleNode head) {
    if (head == null) {
        throw new IllegalArgumentException("Head cannot be null.");
    }
    int count = 0;
    SingleNode current = head;
    while (current != null) {
        count++;
        current = current.next;
    }
    return count;
}

  /**
   * Finds the tail of a singly linked list given the head.
   *
   * @param head the head node of the singly linked list
   * @return the tail node of the list, or null if the list is empty
   * @throws IllegalArgumentException if the head is null
   */
  public static SingleNode findTail(SingleNode head) {
    if (head == null) {
        throw new IllegalArgumentException("Head cannot be null.");
    }
    SingleNode current = head;
    while (current.next != null) {
        current = current.next;
    }
    return current;
}
  /**
   * Finds the head of a doubly linked list given the tail.
   *
   * @param tail the tail node of the doubly linked list
   * @return the head node of the list, or null if the list is empty
   * @throws IllegalArgumentException if the tail is null
   */
  public static DoubleNode findHead(DoubleNode tail) {
    if (tail == null) {
        throw new IllegalArgumentException("Tail cannot be null.");
    }
    DoubleNode current = tail;
    while (current.prev != null) {
        current = current.prev;
    }
    return current;
  }

  /**
   * Counts the occurrences of values in a linked list.
   *
   * @param head the head node of the linked list
   * @return a map where the keys are the values in the list, and the values are the counts of occurrences
   * @throws IllegalArgumentException if the head is null
   */
  public static Map<Integer, Integer> countOccurrences(SingleNode head) {
        if (head == null) {
            throw new IllegalArgumentException("Head cannot be null.");
        }
        Map<Integer, Integer> counts = new HashMap<>();
        SingleNode current = head;
        while (current != null) {
            int val = current.value;
            counts.put(val, counts.getOrDefault(val, 0) + 1);
            current = current.next;
        }
        return counts;
    }

  /**
   * Removes a node from a doubly linked list.
   *
   * @param node the node to remove
   * @throws IllegalArgumentException if the node is null
   */
  public static void removeNode(DoubleNode node) {
    if (node == null) {
        throw new IllegalArgumentException("Node cannot be null.");
    }
    DoubleNode prevNode = node.prev;
    DoubleNode nextNode = node.next;
    if (prevNode != null) {
        prevNode.next = nextNode;
    }
    if (nextNode != null) {
        nextNode.prev = prevNode;
    }
  }

  /**
   * Finds the nth element in a singly linked list.
   *
   * @param head the head node of the singly linked list
   * @param n the index of the element to find (0-based)
   * @return the nth node, or null if the index is out of bounds
   * @throws IllegalArgumentException if the head is null or n is negative
   */
  public static SingleNode findNthElement(SingleNode head, int n) {
    if (head == null || n < 0) {
        throw new IllegalArgumentException("Head cannot be null and n cannot be negative.");
    }
    SingleNode current = head;
    int index = 0;
    while (current != null) {
        if (index == n) {
            return current;
        }
        current = current.next;
        index++;
    }
    return null;
  }

  /**
   * Inserts a new node into a singly linked list given a pointer to a node in the middle of the list.
   *
   * @param node the node after which the new node is to be inserted
   * @param newNode the new node to insert
   * @throws IllegalArgumentException if either node or newNode is null
   */
  public static void insertNode(SingleNode node, SingleNode newNode) {
    if (node == null || newNode == null) {
        throw new IllegalArgumentException("Node and newNode cannot be null.");
    }
    newNode.next = node.next;
    node.next = newNode;
}


  /**
   * Removes all nodes that are strictly larger than their next neighbor in the original list, except for the head.
   * The head is never removed.
   * 
   * The removals are done in-place.
   * 
   * Example:
   * Input: 5 -> 7 -> 6 -> 20 -> 4 -> 4
   * Output: 5 -> 6 -> 4 -> 4
   * 
   * Explanation: 7 is greater than 6 and 20 is greater than 4, so these nodes are removed.
   *
   * @param head the head of the list
   * @throws IllegalArgumentException if the head is null
   */
  public static void removeGiants(SingleNode head) {
    if (head == null) {
        throw new IllegalArgumentException("Head cannot be null.");
    }
    SingleNode prev = head;
    SingleNode current = head.next;
    while (current != null && current.next != null) {
        if (current.value > current.next.value) {
            prev.next = current.next;
            current = current.next;
        } else {
            prev = current;
            current = current.next;
        }
    }
  }


    /**
     * Triples the value of every element in a queue in-place.
     * 
     * Only O(1) space should be used.
     * 
     * You can assume the queue will have first-in-first-out behavior.
     *
     * Example:
     * Input: [5, 3, 2, 7] 
     * Result: [15, 9, 6, 21]
     *
     * @param queue the queue to modify
     * @throws IllegalArgumentException if the queue is null
     */
    public static void tripleValues(Queue<Integer> queue) {
      if (queue == null) {
          throw new IllegalArgumentException("Queue cannot be null");
      }
      int size = queue.size();
      for (int i = 0; i < size; i++) {
          int value = queue.remove();
          queue.add(value * 3);
      }
  }


  /**
   * Rotates a queue to the left by the specified number of positions in-place.
   * 
   * The first k elements of the queue are moved to the end, preserving the order
   * of all elements.
   * 
   * Only O(1) space should be used.
   * 
   * You can assume the queue will have first-in-first-out behavior.
   *
   * Example:
   * Given a queue [1, 2, 3, 4, 5] and k = 2, the result will be [3, 4, 5, 1, 2].
   *
   * @param queue the queue to rotate
   * @param k the number of positions to rotate to the left
   * @throws IllegalArgumentException if the queue is null or k is negative
   */
  public static void rotateQueueLeft(Queue<Integer> queue, int k) {
    if (queue == null || k < 0) {
        throw new IllegalArgumentException("Queue cannot be null and k cannot be negative.");
    }
    int size = queue.size();
    if (size == 0) return;
    k = k % size;
    for (int i = 0; i < k; i++) {
        queue.add(queue.remove());
    }
  }

  /**
   * Checks if a string has balanced parentheses using a stack.
   * 
   * A string is considered to have balanced parentheses if each opening parenthesis
   * '(' has a corresponding closing parenthesis ')', and the parentheses are correctly nested.
   *
   * Example:
   * - Input: "(()())" -> Returns true
   * - Input: "(()" -> Returns false
   * - Input: ")" -> Returns false
   *
   * @param input the string to check
   * @return true if the string has balanced parentheses, false otherwise
   * @throws IllegalArgumentException if the input string is null
   */
  public static boolean hasBalancedParentheses(String input) {
    if (input == null) {
        throw new IllegalArgumentException("Input string cannot be null.");
    }
    Stack<Character> stack = new Stack<>();
    for (char c : input.toCharArray()) {
        if (c == '(') {
            stack.push(c);
        } else if (c == ')') {
            if (stack.isEmpty()) {
                return false;
            }
            stack.pop();
        }
    }
    return stack.isEmpty();
  }

  /**
   * Returns the name of the person who has the highest score associated with them in a map.
   * 
   * The keys hold the names of the players and the values hold the scores. 
   * 
   * For example: 
   * {
   *  "Lewis": 20,
   *  "Yuki": 23,
   *  "Kimi": 16
   * }
   * 
   * Yuki has the highest score.
   * 
   * In the event of a tie, the person whose name comes first lexicographically (alphabetically) should
   * be returned.
   * 
   * @param scores
   * @return the person with the highest score, or the first person lexicographically if there is a tie
   * @throws IllegalArgumentException if the scores are null or empty
   */
  public static String topScorer(Map<String, Integer> scores) {
    if (scores == null || scores.isEmpty()) {
        throw new IllegalArgumentException("Scores cannot be null or empty");
    }
    int maxScore = Integer.MIN_VALUE;
    String bestName = null;
    for (Map.Entry<String, Integer> entry : scores.entrySet()) {
        String name = entry.getKey();
        int score = entry.getValue();
        if (score > maxScore) {
            maxScore = score;
            bestName = name;
        } else if (score == maxScore) {
            if (bestName == null || name.compareTo(bestName) < 0) {
                bestName = name;
            }
        }
    }
    return bestName;
  }
}  

class SingleNode {
  int value;
  SingleNode next;
}

class DoubleNode {
  int value;
  DoubleNode prev;
  DoubleNode next;
}
