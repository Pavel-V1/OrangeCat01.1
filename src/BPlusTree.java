import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class BPlusTree<Num extends Number> {
    int t = 2;
    protected class BPTNode {
        int n;
        ArrayList<Num> value;
        ArrayList<BPTNode> child;
        boolean isLeaf;

        BPTNode(ArrayList<Num> value, Num next) {
            this.value = value;
            this.child = new ArrayList<BPTNode>();
            this.isLeaf = true;
            this.n = value.size();
        }

        public ArrayList<Num> getValue() {
            return value;
        }

        public ArrayList<BPTNode> getChild() {
            return child;
        }
    }

    LinkedList<Num> numLinkedList = new LinkedList<>();

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
            int counter = 0;
            BPTNode result = null;
            for (Num cv : bptNode.value) {
                if (cv.equals(bptNode.value.getLast()) && comp.compare(cv, value) < 0) {
                    result = lookUp(value, bptNode.child.get(counter + 1)); // (n+1)-ый
                    break;
                }
                if (comp.compare(cv, value) < 0) {
                    counter++;
                    continue;
                }
                if (bptNode.isLeaf) {
                    result = bptNode;
                } else {
                    result = lookUp(value, bptNode.child.get(counter));
                }
                break;
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
        ArrayList<Num> arrayList = new ArrayList<>();
        while (comp.compare(i, b) <= 0) {
            arrayList.add(i);
            i = numLinkedList.get(numLinkedList.indexOf(i) + 1);
        }
        return arrayList;
    }

    public void add(Num value) {
        add(value, root);
    }

    private void add(Num value, BPTNode node) {
        if (node == null) {
            node = new BPTNode(new ArrayList<>(), null);
            node.value.add(value);
        } else {
            BPTNode bptNode = lookUp(value, root);
            if (bptNode.value.size() < 2 * t - 1) {
                int counter = 0;
                for (Num v : bptNode.value) {
                    if (comp.compare(v, value) >= 0) {
                        bptNode.value.add(counter, value);
                    } else if (v.equals(bptNode.value.getLast())) {
                        bptNode.value.addLast(value);
                    } else {
                        counter++;
                    }
                }
                int c = 0;
                for (Num i : bptNode.value) {
                    if (i.equals(value)) {
                        break;
                    } else {
                        c++;
                    }
                }
                numLinkedList.add(numLinkedList.indexOf(bptNode.value.getFirst()) + c, value);
            } else {

            }
        }
    }

//    public boolean delete(Num value) {
//        try {
//            Integer v = search(value);
//            //
//
//            //ListNode d = cur.next.prev;
//            //cur.next = cur.next.next;
//            //cur.next.prev = d;
//            //return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public boolean delete(Num a, Num b) {
//        boolean bool = true;
//        ArrayList<Num> idArray = search(a, b);
//        for (Num umnum : idArray) {
//            delete(umnum);
//        }
//        return bool;
//    }
}