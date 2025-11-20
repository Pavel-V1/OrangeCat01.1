import java.util.ArrayList;
import java.util.Comparator;

public class BPlusTree<Num extends Number> {
    protected class BPTNode {
        int n;
        ArrayList<Num> value;
        ArrayList<BPTNode> child;
        boolean isLeaf;
        BPTNode next;

        BPTNode(ArrayList<Num> value, BPTNode next) {
            this.value = value;
            this.child = new ArrayList<BPTNode>();
            this.isLeaf = true;
            this.n = value.size();
            this.next = next;
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

    protected BPTNode lookUp(Num value, BPTNode bptNode) {
        if (bptNode == null) {
            return null;
        } else {
            BPTNode cur = bptNode;
            int counter = 0;
            BPTNode result = null;
            for (Num cv : cur.value) {
                if (comp.compare(cv, value) < 0) {
                    counter++;
                    continue;
                } else if (!cur.isLeaf) {
                    result = lookUp(value, cur.child.get(counter));
                } else {
                    break;
                }
            }
            return result;
        }
    }

    public Num search(Num value) {
        return lookUp(value, root).value.get(0);
    }

    public ArrayList<Num> search(Num a, Num b) {
        BPTNode bptNode = lookUp(a, root);
        Num i = a;
        ArrayList<Num> numArrayList = new ArrayList<>();
        while (comp.compare(i, b) <= 0) {
            numArrayList.add(i);
            i = bptNode.next.value.get(0);
        }
        return numArrayList;
    }

    public void add(Num value) {
        if (root == null) {
            root = new BPTNode(new ArrayList<>(), null);
            root.value.add(value);
        } else {
            BPTNode bptNode = lookUp(value, root);
            //
        }
    }

    private void splitNode(BPTNode bptNode) {
        if (bptNode != null) {
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