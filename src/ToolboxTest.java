import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ToolboxTest {

    // ----------------------------------------------------------------------
    // length(SingleNode head)
    // ----------------------------------------------------------------------

    @Test
    void testLengthSingleElement() {
        SingleNode head = new SingleNode(7);
        assertEquals(1, Toolbox.length(head));
    }

    @Test
    void testLengthMultipleElements() {
        // SLL: 3 -> 1 -> 4 -> 1 -> 5
        SingleNode head = SingleNode.fromList(Arrays.asList(3, 1, 4, 1, 5));
        assertEquals(5, Toolbox.length(head));
    }

    @Test
    void testLengthLongerList() {
        // SLL: 0 -> -1 -> 2 -> -3 -> 4 -> -5 -> 6
        SingleNode head = SingleNode.fromList(Arrays.asList(0, -1, 2, -3, 4, -5, 6));
        assertEquals(7, Toolbox.length(head));
    }

    @Test
    void testLengthNullHead() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.length(null));
    }

    // ----------------------------------------------------------------------
    // findTail(SingleNode head)
    // ----------------------------------------------------------------------

    @Test
    void testFindTailMultipleElements() {
        // SLL: 19 -> 42 -> 7
        SingleNode head = new SingleNode(19, new SingleNode(42, new SingleNode(7)));
        SingleNode tail = Toolbox.findTail(head);
        assertNotNull(tail);
        assertEquals(7, tail.data);
        assertNull(tail.next);
    }

    @Test
    void testFindTailTwoElements() {
        // SLL: 100 -> 200
        SingleNode head = new SingleNode(100, new SingleNode(200));
        SingleNode tail = Toolbox.findTail(head);
        assertEquals(head.next, tail);
        assertEquals(200, tail.data);
        assertNull(tail.next);
    }

    @Test
    void testFindTailSingleElement() {
        SingleNode head = new SingleNode(500);
        SingleNode tail = Toolbox.findTail(head);
        assertEquals(head, tail);
        assertEquals(500, tail.data);
        assertNull(tail.next);
    }

    @Test
    void testFindTailNullHead() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.findTail(null));
    }

    // ----------------------------------------------------------------------
    // findHead(DoubleNode tail)
    // ----------------------------------------------------------------------

    @Test
    void testFindHeadMultipleElements() {
        // DLL: 27 <-> 11 <-> 83
        DoubleNode n1 = new DoubleNode(27);
        DoubleNode n2 = new DoubleNode(11);
        DoubleNode n3 = new DoubleNode(83);
        n1.next = n2; n2.prev = n1;
        n2.next = n3; n3.prev = n2;

        DoubleNode head = Toolbox.findHead(n3);
        assertEquals(n1, head);
        assertNull(head.prev);
    }

    @Test
    void testFindHeadSingleElement() {
        DoubleNode tail = new DoubleNode(999);
        DoubleNode head = Toolbox.findHead(tail);
        assertEquals(tail, head);
        assertEquals(999, head.data);
        assertNull(head.prev);
        assertNull(head.next);
    }

    @Test
    void testFindHeadNullTail() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.findHead(null));
    }

    // ----------------------------------------------------------------------
    // countOccurrences(SingleNode head)
    // ----------------------------------------------------------------------

    @Test
    void testCountOccurrencesMultiple() {
        // SLL: 5 -> 12 -> 5 -> 3 -> 5 -> 12
        SingleNode head = SingleNode.fromList(Arrays.asList(5, 12, 5, 3, 5, 12));
        Map<Integer, Integer> counts = Toolbox.countOccurrences(head);
        // Expect {5=3, 12=2, 3=1}
        assertEquals(3, counts.size());
        assertEquals(Integer.valueOf(3), counts.get(5));
        assertEquals(Integer.valueOf(2), counts.get(12));
        assertEquals(Integer.valueOf(1), counts.get(3));
    }

    @Test
    void testCountOccurrencesAllSame() {
        // SLL: 8 -> 8 -> 8 -> 8
        SingleNode head = SingleNode.fromList(Arrays.asList(8, 8, 8, 8));
        Map<Integer, Integer> counts = Toolbox.countOccurrences(head);
        // Expect {8=4}
        assertEquals(1, counts.size());
        assertEquals(Integer.valueOf(4), counts.get(8));
    }

    @Test
    void testCountOccurrencesSingleElement() {
        SingleNode head = new SingleNode(66);
        Map<Integer, Integer> counts = Toolbox.countOccurrences(head);
        // Expect {66=1}
        assertEquals(1, counts.size());
        assertEquals(Integer.valueOf(1), counts.get(66));
    }

    @Test
    void testCountOccurrencesNullHead() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.countOccurrences(null));
    }

    // ----------------------------------------------------------------------
    // removeNode(DoubleNode node)
    // ----------------------------------------------------------------------

    @Test
    void testRemoveNodeMiddle() {
        // DLL: 8 <-> 99 <-> 13 <-> 23
        DoubleNode n1 = new DoubleNode(8);
        DoubleNode n2 = new DoubleNode(99);
        DoubleNode n3 = new DoubleNode(13);
        DoubleNode n4 = new DoubleNode(23);
        n1.next = n2; n2.prev = n1;
        n2.next = n3; n3.prev = n2;
        n3.next = n4; n4.prev = n3;

        Toolbox.removeNode(n2);
        // Expected: 8 <-> 13 <-> 23
        assertEquals(n1, n3.prev);
        assertEquals(n3, n1.next);
        assertEquals(n4, n3.next);
        assertEquals(n3, n4.prev);
    }

    @Test
    void testRemoveNodeFirst() {
        // DLL: 8 <-> 99
        DoubleNode n1 = new DoubleNode(8);
        DoubleNode n2 = new DoubleNode(99);
        n1.next = n2; n2.prev = n1;

        Toolbox.removeNode(n1);
        // Expected: just n2 left
        assertNull(n2.prev);
        assertNull(n2.next);
    }

    @Test
    void testRemoveNodeLast() {
        // DLL: 8 <-> 99
        DoubleNode n1 = new DoubleNode(8);
        DoubleNode n2 = new DoubleNode(99);
        n1.next = n2; n2.prev = n1;

        Toolbox.removeNode(n2);
        // Expected: just n1 left
        assertNull(n1.next);
        assertNull(n1.prev);
    }

    @Test
    void testRemoveNodeSolo() {
        // DLL: single node
        DoubleNode solo = new DoubleNode(42);
        Toolbox.removeNode(solo);
        // Nothing remains; pointers stay null
        assertNull(solo.prev);
        assertNull(solo.next);
        assertEquals(42, solo.data);
    }

    @Test
    void testRemoveNodeNull() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.removeNode(null));
    }

    // ----------------------------------------------------------------------
    // findNthElement(SingleNode head, int n)
    // ----------------------------------------------------------------------

    @Test
    void testFindNthElementIndexZero() {
        SingleNode head = SingleNode.fromList(Arrays.asList(9, 8, 7));
        SingleNode result = Toolbox.findNthElement(head, 0);
        assertSame(head, result);
        assertEquals(9, result.data);
    }

    @Test
    void testFindNthElementValidIndex() {
        // SLL: 7 -> 19 -> 3 -> 4
        SingleNode head = SingleNode.fromList(Arrays.asList(7, 19, 3, 4));
        SingleNode result = Toolbox.findNthElement(head, 1);
        assertNotNull(result);
        assertEquals(19, result.data);
    }

    @Test
    void testFindNthElementLastIndex() {
        // SLL: 7 -> 19 -> 3 -> 4
        SingleNode head = SingleNode.fromList(Arrays.asList(7, 19, 3, 4));
        SingleNode result = Toolbox.findNthElement(head, 3);
        assertNotNull(result);
        assertEquals(4, result.data);
    }

    @Test
    void testFindNthElementOutOfBounds() {
        SingleNode head = SingleNode.fromList(Arrays.asList(10, 5));
        SingleNode result = Toolbox.findNthElement(head, 5);
        assertNull(result);
    }

    @Test
    void testFindNthElementNullHead() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.findNthElement(null, 0));
    }

    @Test
    void testFindNthElementNegativeIndex() {
        SingleNode head = new SingleNode(1);
        assertThrows(IllegalArgumentException.class, () -> Toolbox.findNthElement(head, -1));
    }

    // ----------------------------------------------------------------------
    // insertNode(SingleNode node, SingleNode newNode)
    // ----------------------------------------------------------------------

    @Test
    void testInsertNodeInMiddle() {
        // SLL: 10 -> 5 -> 19
        SingleNode head = SingleNode.fromList(Arrays.asList(10, 5, 19));
        SingleNode node = head.next; // data=5
        SingleNode newNode = new SingleNode(7);

        Toolbox.insertNode(node, newNode);
        // Expected: 10 -> 5 -> 7 -> 19
        assertEquals(Arrays.asList(10, 5, 7, 19), head.toList());
    }

    @Test
    void testInsertNodeAfterLast() {
        // SLL: 10 -> 5
        SingleNode head = SingleNode.fromList(Arrays.asList(10, 5));
        SingleNode lastNode = head.next; // data=5
        SingleNode newNode = new SingleNode(33);

        Toolbox.insertNode(lastNode, newNode);
        // Expected: 10 -> 5 -> 33
        assertEquals(Arrays.asList(10, 5, 33), head.toList());
    }

    @Test
    void testInsertNodeNullNode() {
        SingleNode newNode = new SingleNode(5);
        assertThrows(IllegalArgumentException.class, () -> Toolbox.insertNode(null, newNode));
    }

    @Test
    void testInsertNodeNullNewNode() {
        SingleNode head = new SingleNode(1);
        assertThrows(IllegalArgumentException.class, () -> Toolbox.insertNode(head, null));
    }

    // ----------------------------------------------------------------------
    // removeGiants(SingleNode head)
    // ----------------------------------------------------------------------

    @Test
    void testRemoveGiantsExample() {
        // Input: 5 -> 7 -> 6 -> 20 -> 4 -> 4
        SingleNode head = SingleNode.fromList(Arrays.asList(5, 7, 6, 20, 4, 4));
        Toolbox.removeGiants(head);
        // Expected output: 5 -> 6 -> 4 -> 4
        assertEquals(Arrays.asList(5, 6, 4, 4), head.toList());
    }

    @Test
    void testRemoveGiantsNoRemovals() {
        // Input: 1 -> 2 -> 3 -> 4
        SingleNode head = SingleNode.fromList(Arrays.asList(1, 2, 3, 4));
        Toolbox.removeGiants(head);
        // No node strictly larger than next, so list stays same
        assertEquals(Arrays.asList(1, 2, 3, 4), head.toList());
    }

    @Test
    void testRemoveGiantsHeadNeverRemoved() {
        // Input: 10 -> 1 -> 2
        SingleNode head = SingleNode.fromList(Arrays.asList(10, 1, 2));
        Toolbox.removeGiants(head);
        // Even though 10 > 1, head remains; then 1 < 2 so only check 10->1 removes nothing because head is preserved
        assertEquals(Arrays.asList(10, 1, 2), head.toList());
    }

    @Test
    void testRemoveGiantsSingleElement() {
        SingleNode head = new SingleNode(42);
        Toolbox.removeGiants(head);
        assertEquals(Arrays.asList(42), head.toList());
    }

    @Test
    void testRemoveGiantsNullHead() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.removeGiants(null));
    }

    // ----------------------------------------------------------------------
    // tripleValues(Queue<Integer> queue)
    // ----------------------------------------------------------------------

    @Test
    void testTripleValuesTypical() {
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(5, 3, 2, 7));
        Toolbox.tripleValues(queue);
        assertEquals(Arrays.asList(15, 9, 6, 21), new ArrayList<>(queue));
    }

    @Test
    void testTripleValuesWithZeroAndNegative() {
        Queue<Integer> queue = new ArrayDeque<>(Arrays.asList(0, -1, 2));
        Toolbox.tripleValues(queue);
        assertEquals(Arrays.asList(0, -3, 6), new ArrayList<>(queue));
    }

    @Test
    void testTripleValuesEmptyQueue() {
        Queue<Integer> queue = new LinkedList<>();
        Toolbox.tripleValues(queue);
        assertTrue(queue.isEmpty());
    }

    @Test
    void testTripleValuesNullQueue() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.tripleValues(null));
    }

    // ----------------------------------------------------------------------
    // rotateQueueLeft(Queue<Integer> queue, int k)
    // ----------------------------------------------------------------------

    @Test
    void testRotateQueueLeftValid() {
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(3, 9, 1, 14, 8));
        Toolbox.rotateQueueLeft(queue, 2);
        assertEquals(Arrays.asList(1, 14, 8, 3, 9), new ArrayList<>(queue));
    }

    @Test
    void testRotateQueueLeftZero() {
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(10, 25, 7));
        Toolbox.rotateQueueLeft(queue, 0);
        assertEquals(Arrays.asList(10, 25, 7), new ArrayList<>(queue));
    }

    @Test
    void testRotateQueueLeftLargeK() {
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(3, 9, 1, 14, 8));
        Toolbox.rotateQueueLeft(queue, 7);
        assertEquals(Arrays.asList(1, 14, 8, 3, 9), new ArrayList<>(queue));
    }

    @Test
    void testRotateQueueLeftNullQueue() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.rotateQueueLeft(null, 2));
    }

    @Test
    void testRotateQueueLeftNegativeK() {
        Queue<Integer> queue = new ArrayDeque<>(Arrays.asList(1, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> Toolbox.rotateQueueLeft(queue, -1));
    }

    // ----------------------------------------------------------------------
    // hasBalancedParentheses(String input)
    // ----------------------------------------------------------------------

    @Test
    void testHasBalancedParenthesesBalanced() {
        assertTrue(Toolbox.hasBalancedParentheses("(()())"));
    }

    @Test
    void testHasBalancedParenthesesUnmatched() {
        assertFalse(Toolbox.hasBalancedParentheses("(()"));
        assertFalse(Toolbox.hasBalancedParentheses("())"));
    }

    @Test
    void testHasBalancedParenthesesMismatch() {
        assertFalse(Toolbox.hasBalancedParentheses(")("));
    }

    @Test
    void testHasBalancedParenthesesEmptyString() {
        assertTrue(Toolbox.hasBalancedParentheses(""));
    }

    @Test
    void testHasBalancedParenthesesNullInput() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.hasBalancedParentheses(null));
    }

    // ----------------------------------------------------------------------
    // topScorer(Map<String, Integer> scores)
    // ----------------------------------------------------------------------

    @Test
    void testTopScorerTypical() {
        Map<String, Integer> scores = Map.of(
            "Lewis", 20,
            "Yuki", 23,
            "Kimi", 16
        );
        assertEquals("Yuki", Toolbox.topScorer(scores));
    }

    @Test
    void testTopScorerTieLexOrder() {
        Map<String, Integer> scores = Map.of(
            "Alice", 50,
            "Bob", 50,
            "Charlie", 40
        );
        // Alice and Bob tie at 50, Alice wins lexicographically
        assertEquals("Alice", Toolbox.topScorer(scores));
    }

    @Test
    void testTopScorerAllTie() {
        Map<String, Integer> scores = Map.of(
            "C", 5,
            "A", 5,
            "B", 5
        );
        // All tie at 5, A is lexicographically first
        assertEquals("A", Toolbox.topScorer(scores));
    }

    @Test
    void testTopScorerSingleEntry() {
        Map<String, Integer> scores = Map.of("Solo", 100);
        assertEquals("Solo", Toolbox.topScorer(scores));
    }

    @Test
    void testTopScorerNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Toolbox.topScorer(null));
        assertThrows(IllegalArgumentException.class, () -> Toolbox.topScorer(Map.of()));
    }
}