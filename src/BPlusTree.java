public class BPlusTree {
    protected class BPTNode {
        float value;
        BPTNode bigger;
        BPTNode lower;

        BPTNode(float value) {
            this.value = value;
            this.bigger = null;
            this.lower = null;
        }

        public float getValue() {
            return value;
        }

        public BPTNode getBigger() {
            return bigger;
        }

        public BPTNode getLower() {
            return lower;
        }
    }

    private BPTNode root;
    public BPlusTree() {
        this.root = null;
    }

    // public void add(int value) {}
    public void add(float value) {
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

    public int search(float value) {
        BPTNode cur = root;
        //
        return 0;
    }

    public int search(float a, float b) {
        //
        return 0;
    }

    public boolean delete(float value) {
        //
        return false;
    }

    public boolean delete(float a, float b) {
        boolean bool = true;
        for (float i = a; i <= b; i++) {
            boolean del_bool = delete(i);
            if (!del_bool) {
                bool = false;
            }
        }
        return bool;
    }
}