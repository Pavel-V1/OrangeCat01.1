import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class BPlusTree<Num extends Number> {
    int t = 2;

    protected class BPTNode {
        ArrayList<Num> value;
        ArrayList<BPTNode> child;
        boolean isLeaf;

        BPTNode(ArrayList<Num> value, ArrayList<BPTNode> child, boolean isLeaf) {
            this.value = value;
            this.child = child;
            this.isLeaf = isLeaf;
        }

        public ArrayList<Num> getValue() {
            return value;
        }

        public ArrayList<BPTNode> getChild() {
            return child;
        }
    }

    Comparator<Num> comp = new Comparator<Num>() {
        @Override
        public int compare(Num o1, Num o2) {
            if ((float) o1 - (float) o2 > 0) {
                return 1;
            } else if ((float) o1 - (float) o2 < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    private BPTNode root;
    public BPlusTree() {
        this.root = null;
    }

    public void add(Num value) {
        if (root == null) {
            root = new BPTNode(new ArrayList<>(), null, true);
            root.value.add(value);
        } else {
            Integer v = search(value);
            //
        }
    }

    public Integer search(Num value) {
        if (root == null) {
            return null;
        } else {
            BPTNode cur = root;
            for (Num cv : cur.value) {
                //
            }
        }
    }

    public ArrayList<Integer> search(Num a, Num b) {
        Integer startID = search(a);
        if (startID == null) {
            return null;
        } else {
            //
        }
    }

//    public boolean delete(Num value) {
//        try {
//            Integer v = search(value);
//            //
//            ListNode d = cur.next.prev;
//            cur.next = cur.next.next;
//            cur.next.prev = d;
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private Num add1(Num i) {
//        return comp.compare(i, -1);
//    }
//
//    public boolean delete(Num a, Num b) {
//        boolean bool = true;
//        for (Num i = a; comp.compare(i, b) >= 0; add1(i)) {
//            boolean del_bool = delete(i);
//            if (!del_bool) {
//                bool = false;
//                break;
//            }
//        }
//        return bool;
//    }
}