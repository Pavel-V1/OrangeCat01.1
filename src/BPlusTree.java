import java.util.ArrayList;
import java.util.HashMap;

public class BPlusTree {
    private int t;

    private class OrangeMeowBucket {
        Float key;
        Integer value;

        OrangeMeowBucket(Float key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Float getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public void setKey(Float key) {
            this.key = key;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    private class BPTNode {
        ArrayList<Float> keys;
        ArrayList<BPTNode> children;
        BPTNode parent;
        boolean isLeaf;
        int n;
        BPTNode next;
        ArrayList<OrangeMeowBucket> idKey;

        BPTNode(ArrayList<Float> keys, ArrayList<BPTNode> children, BPTNode parent, BPTNode next) {
            this.keys = keys;
            this.children = children;
            this.parent = parent;
            this.isLeaf = true;
            this.n = keys.size();
            this.next = next;
            this.idKey = null;
        }

        public ArrayList<Float> getKeys() {
            return keys;
        }

        public ArrayList<BPTNode> getChildren() {
            return children;
        }

        public ArrayList<Integer> getId() {
            ArrayList<Integer> arr = new ArrayList<>();
            for (OrangeMeowBucket car : idKey) {
                arr.add(car.value);
            }
            return arr;
        }
    }

    private BPTNode root;
    public BPlusTree(int t) {
        this.root = null;
        this.t = t;
    }

    private BPTNode lookUp(Float value, BPTNode bptNode) {
        if (bptNode == null) {
            return null;
        } else {
            int counter = 0;
            BPTNode result = null;
            for (Float curv : bptNode.keys) {
                if (curv.equals(bptNode.keys.get(bptNode.n - 1)) && curv < value) {
                    result = lookUp(value, bptNode.children.get(counter + 1)); // (n+1)-ый
//                    result = lookUp(value, bptNode.children.get(bptNode.n));
                    break;
                } else if (curv < value) {
                    counter++;
                    continue;
                } else if (bptNode.isLeaf && curv.equals(value)) {
                    result = bptNode;
                    break;
                } else {
                    result = lookUp(value, bptNode.children.get(counter));
                    break;
                }
            }
            return result;
        }
    }

    public ArrayList<Integer> search(Float value) {
        return search(value, value);
    }

    public ArrayList<Integer> search(Float a, Float b) {
        if (a > b) {
            Float f = a;
            a = b;
            b = f;
        }
        BPTNode bptNode = lookUp(a, root);
        OrangeMeowBucket meow = bptNode.idKey.get(bptNode.keys.indexOf(a));
        ArrayList<Integer> arr = new ArrayList<>();
        while (meow.key <= b) {
            arr.add(meow.value);
            if (meow.equals(bptNode.idKey.get(bptNode.n - 1))) {
                meow = bptNode.next.idKey.getFirst();
            } else {
                meow = bptNode.idKey.get(bptNode.idKey.indexOf(meow) + 1);
            }
        }
        return arr;
    }

    public void add(Float value, Integer id) {
        BPTNode node = lookUp(value, root);

        if (node == null) {
            node = new BPTNode(new ArrayList<>(), new ArrayList<>(), root, null);
            node.keys.add(value);
            node.idKey = new ArrayList<>();
            node.idKey.add(new OrangeMeowBucket(value, id));
            node.n = 1;
            if (root == null) {
                root = node;
            } else {
                boolean isFull = root.n == 2 * t - 1;
                for (Float key : root.getKeys()) {
                    if (key > value) {
                        root.children.add(root.keys.indexOf(key), node);
                        break;
                    }
                }
                if (isFull) {
                    split(root);
                }
                if (node != root.children.get(root.n - 1)) {
                    node.next = root.children.get(root.children.indexOf(node) + 1);
                }
                if (node != root.children.getFirst()) {
                    root.children.get(root.children.indexOf(node) - 1).next = node;
                }
            }
        } else {
            boolean isFull = node.n == 2 * t - 1;
            // конечно размер выйдет за пределы допустимого на время, но всё равно эту ноду сотрем
            for (Float key : node.keys) {
                if (value <= key) {
                    node.keys.add(node.keys.indexOf(key), value);
                    node.idKey.add(node.keys.indexOf(key), new OrangeMeowBucket(value, id));
                    node.n += 1;
                    break;
                } else if (key.equals(node.keys.get(node.n - 1))) {
                    node.keys.add(value);
                    node.idKey.add(new OrangeMeowBucket(value, id));
                    node.n += 1;
                    break;
                }
            }
            if (isFull) {
                split(node);
            }
        }

        valueFromId.put(id, value);
    }

    private void split(BPTNode bptNode) {
        BPTNode bptNode2 = new BPTNode(new ArrayList<>(), new ArrayList<>(), bptNode, bptNode.next);
        BPTNode bptNode1 = new BPTNode(new ArrayList<>(), new ArrayList<>(), bptNode, bptNode2);
        bptNode.parent.children.get(bptNode.parent.children.indexOf(bptNode) - 1).next = bptNode1;

        int c = 0;
        Float keyMedium = null;
        for (Float key : bptNode.keys) {
            c++;
            if (c > t) {
                keyMedium = key;
                break;
            }
            bptNode1.keys.add(key);
        }
        if (!bptNode.isLeaf) {
            bptNode1.isLeaf = false;
            for (BPTNode child : bptNode.children) {
                if (bptNode.children.indexOf(child) + 1 > t) {
                    break;
                }
                bptNode1.children.add(child);
                child.parent = bptNode1;
            }
        }
        bptNode1.n = bptNode1.keys.size();

        for (Float key : bptNode.keys) {
            if (key >= keyMedium) {
                bptNode2.keys.add(key);
            }
        }
        if (!bptNode.isLeaf) {
            bptNode2.isLeaf = false;
            for (BPTNode child : bptNode.children) {
                if (bptNode.children.indexOf(child) + 1 > t) {
                    bptNode2.children.add(child);
                    child.parent = bptNode2;
                }
            }
        }
        bptNode2.n = bptNode2.keys.size();

        if (bptNode.parent != null) {
            bptNode1.parent = bptNode.parent;
            bptNode2.parent = bptNode.parent;
            int i = bptNode.parent.children.indexOf(bptNode);
            bptNode.parent.keys.add(i, keyMedium);
            bptNode.parent.n = bptNode.parent.keys.size();
            bptNode.parent.children.remove(i);
            bptNode.parent.children.add(i, bptNode1);
            bptNode.parent.children.add(i + 1, bptNode2);
        } else {
            root = new BPTNode(new ArrayList<>(), new ArrayList<>(), null, null);
            root.children.add(bptNode1);
            root.children.add(bptNode2);
            root.keys.add(keyMedium);
            root.n = 1;
            root.isLeaf = false;
        }

        if (bptNode1.parent.n == 2 * t - 1) {
            split(bptNode1.parent);
        }
    }

    private HashMap<Integer, Float> valueFromId = new HashMap<>();

    public boolean delete(Integer integer) {
        Float key = valueFromId.get(integer);
        BPTNode node = lookUp(key, root);
        if (node == null) {
            return false;
        } else {
            node.idKey.removeIf(IWantToSleep -> IWantToSleep.value.equals(integer));

            valueFromId.remove(integer);
            return true;
        }
    }

    // не знаю, зачем вообще это может понадобиться...
    public boolean delete(Float value) {
        return delete(value, value);
    }

    public boolean delete(Float a, Float b) {
        if (a > b) {
            Float f = a;
            a = b;
            b = f;
        }
        boolean bool = true;
        ArrayList<Integer> idArray = search(a, b);
        for (Integer umnum : idArray) {
            bool = delete(umnum);
            if (!bool) {
                break;
            }
        }
        return bool;
    }
}
