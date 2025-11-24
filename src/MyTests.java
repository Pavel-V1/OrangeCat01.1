import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MyTests {
    @Test
    public void testBPlusTree() {
        BPlusTree bpTree = new BPlusTree(5);
        bpTree.add((float) 234.3, 7374);
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(7374);
        Assert.assertEquals(arr, bpTree.search((float) 234.3));
    }
}