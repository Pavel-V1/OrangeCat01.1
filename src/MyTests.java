import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MyTests {
    @Test
    public void testBPlusTree() {
        BPlusTree bpt = new BPlusTree(3);
        Assert.assertFalse(bpt.delete(234));
        Assert.assertFalse(bpt.delete((float) 234.3));
        Assert.assertNull(bpt.search((float) 234.4, (float) 235));
        Assert.assertNull(bpt.search((float) 234.4));
        bpt.add((float) 234.3, 7374);
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(7374);
        Assert.assertEquals(arr, bpt.search((float) 234.3));
        Assert.assertTrue(bpt.delete(7374));
        Assert.assertNull(bpt.search((float) 234.4));
    }
}