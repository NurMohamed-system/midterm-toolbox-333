import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Collections;

public class DoubleNodeTest {

  @Test
  public void testToListWithSingleElement() {
    DoubleNode node = new DoubleNode(77);
    List<Integer> result = node.toList();
    assertEquals(List.of(77), result);
  }

  @Test
  public void testToListWithMultipleElements() {
    DoubleNode node5 = new DoubleNode(14);
    DoubleNode node4 = new DoubleNode(36, node5);
    DoubleNode node3 = new DoubleNode(9, node4);
    DoubleNode node2 = new DoubleNode(22, node3);
    DoubleNode node1 = new DoubleNode(81, node2);

    node2.prev = node1;
    node3.prev = node2;
    node4.prev = node3;
    node5.prev = node4;

    List<Integer> result = node1.toList();
    assertEquals(List.of(81, 22, 9, 36, 14), result);
  }

  @Test
  public void testFromListWithEmptyList() {
    assertThrows(IllegalArgumentException.class,
        () -> DoubleNode.fromList(Collections.emptyList()));
  }

  @Test
  public void testFromListWithSingleElement() {
    DoubleNode head = DoubleNode.fromList(List.of(64));
    assertNotNull(head);
    assertEquals(64, head.data);
    assertNull(head.next);
    assertNull(head.prev);
  }

  @Test
  public void testFromListWithMultipleElements() {
    DoubleNode head = DoubleNode.fromList(List.of(3, 7, 20, 55));

    // Verify the doubly linked list structure
    assertNotNull(head);
    assertEquals(3, head.data);
    assertNotNull(head.next);
    assertEquals(7, head.next.data);
    assertEquals(head, head.next.prev);
    assertNotNull(head.next.next);
    assertEquals(20, head.next.next.data);
    assertEquals(head.next, head.next.next.prev);
    assertNotNull(head.next.next.next);
    assertEquals(55, head.next.next.next.data);
    assertEquals(head.next.next, head.next.next.next.prev);
    assertNull(head.next.next.next.next);
  }

  @Test
  public void testFromListToListRoundTrip() {
    List<Integer> originalList = List.of(12, 45, 78, 34, 19);
    DoubleNode head = DoubleNode.fromList(originalList);
    List<Integer> resultList = head.toList();

    assertEquals(originalList, resultList);
  }

  @Test
  public void testToListAndFromListWithEdgeCaseValues() {
    List<Integer> edgeCaseList = List.of(Integer.MIN_VALUE, 101, -87, 0, Integer.MAX_VALUE);
    DoubleNode head = DoubleNode.fromList(edgeCaseList);
    List<Integer> resultList = head.toList();

    assertEquals(edgeCaseList, resultList);
  }

  @Test
  public void testFromListAndNullHandling() {
    assertThrows(IllegalArgumentException.class,
      () -> DoubleNode.fromList(null));
  }
}
