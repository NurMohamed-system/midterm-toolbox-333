import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Collections;

public class SingleNodeTest {

  @Test
  public void testToListWithSingleElement() {
    SingleNode node = new SingleNode(99);
    List<Integer> result = node.toList();
    assertEquals(List.of(99), result);
  }

  @Test
  public void testToListWithMultipleElements() {
    SingleNode node5 = new SingleNode(23);
    SingleNode node4 = new SingleNode(89, node5);
    SingleNode node3 = new SingleNode(15, node4);
    SingleNode node2 = new SingleNode(42, node3);
    SingleNode node1 = new SingleNode(67, node2);

    List<Integer> result = node1.toList();
    assertEquals(List.of(67, 42, 15, 89, 23), result);
  }

  @Test
  public void testToListWithEmptyNode() {
    SingleNode node = new SingleNode(-8, null); // Node exists but has no next
    List<Integer> result = node.toList();
    assertEquals(List.of(-8), result);
  }

  @Test
  public void testFromListWithEmptyList() {
    assertThrows(IllegalArgumentException.class,
    () -> SingleNode.fromList(Collections.emptyList()));

  }

  @Test
  public void testFromListWithSingleElement() {
    SingleNode head = SingleNode.fromList(List.of(112));
    assertNotNull(head);
    assertEquals(112, head.data);
    assertNull(head.next);
  }

  @Test
  public void testFromListWithMultipleElements() {
    SingleNode head = SingleNode.fromList(List.of(50, 3, 99, 17));

    // Verify the linked list structure
    assertNotNull(head);
    assertEquals(50, head.data);
    assertNotNull(head.next);
    assertEquals(3, head.next.data);
    assertNotNull(head.next.next);
    assertEquals(99, head.next.next.data);
    assertNotNull(head.next.next.next);
    assertEquals(17, head.next.next.next.data);
    assertNull(head.next.next.next.next);
  }

  @Test
  public void testFromListToListRoundTrip() {
    List<Integer> originalList = List.of(91, 43, 76, 88, 25);
    SingleNode head = SingleNode.fromList(originalList);
    List<Integer> resultList = head.toList();

    assertEquals(originalList, resultList);
  }

  
}
