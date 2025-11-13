class BPTNode {
    int value;
    BPTNode bigger;
    BPTNode lower;

    BPTNode(int value) {
        this.value = value;
        this.bigger = null;
        this.lower = null;
    }

    public int getValue() {
        return value;
    }

    public BPTNode getBigger() {
        return bigger;
    }

    public BPTNode getLower() {
        return lower;
    }
}

public class BPlusTree {
    private BPTNode root;
    public BPlusTree() {
        this.root = null;
    }

    public void add(int value) {
        BPTNode bptNode = new BPTNode(value);
        if (root == null) {
            root = bptNode;
        } else {
            if (value == root.value) {
                //
            } else if (value < root.value) {
                //
            } else if (value > root.value) {
                //
            }
        }
    }

    public BPTNode Lookup(int k) {
        BPTNode cur = root;
        //
        return null;
    }

    public BPTNode Lookup(int a, int b) {
        //
        return null;
    }
}