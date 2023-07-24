package PBL2;
public class BinaryTree {
    Object root;
    BinaryTree left,right;
    public BinaryTree(Object root){this.root=root;}
    public BinaryTree(Object root, BinaryTree left, BinaryTree right){
        this.root=root;
        this.left=left;
        this.right=right;
    }

    public Object getRoot() {
        return root;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public BinaryTree getRight() {
        return right;
    }
    public String getSmallest(){
        BinaryTree temp=this;
        int leftSmall,rightSmall,temporary;
        String[] parts=String.valueOf(temp).split(",");
        leftSmall=Integer.parseInt(parts[0]);
        rightSmall=Integer.parseInt(parts[0]);
        String monthLeft=parts[1],monthRight=parts[1];
        while (temp.left!=null){
            parts=String.valueOf(temp.left.root).split(",");
            temporary=Integer.parseInt(parts[0]);
            if(temporary<leftSmall){
                leftSmall=temporary;
                monthLeft=parts[1];
            }
            temp=temp.left;
        }
        temp=this;
        while (temp.right!=null){
            parts=String.valueOf(temp.right.root).split(",");
            temporary=Integer.parseInt(parts[0]);
            if(temporary<rightSmall){
                rightSmall=temporary;
                monthRight=parts[1];
            }
            temp=temp.right;
        }
        if(leftSmall<rightSmall)return leftSmall+", crashes in "+monthLeft;
        return rightSmall+", crashes in "+monthRight;
    }
    public String getLargest(){
        BinaryTree temp=this;
        int leftLarge,rightLarge,temporary;
        String[] parts=String.valueOf(temp).split(",");
        leftLarge=Integer.parseInt(parts[0]);
        rightLarge=Integer.parseInt(parts[0]);
        String monthLeft=parts[1],monthRight=parts[1];
        while (temp.left!=null){
            parts=String.valueOf(temp.left.root).split(",");
            temporary=Integer.parseInt(parts[0]);
            if(temporary>leftLarge){
                leftLarge=temporary;
                monthLeft=parts[1];
            }
            temp=temp.left;
        }
        temp=this;
        while (temp.right!=null){
            parts=String.valueOf(temp.right.root).split(",");
            temporary=Integer.parseInt(parts[0]);
            if(temporary>rightLarge){
                rightLarge=temporary;
                monthRight=parts[1];
            }
            temp=temp.right;
        }
        if(leftLarge>rightLarge)return leftLarge+", crashes in "+monthLeft;
        return rightLarge+", crashes in "+monthRight;
    }
    public boolean isLeaf(){return left==null && right==null;}
    public void add(String side, Object noOfCases){
        BinaryTree temp=this;
        if(side.equals("left")){
            while (true) {
                if (temp.left == null) {
                    temp.left = new BinaryTree(noOfCases);
                    return;
                }
                temp = temp.left;
            }
        }
        else if(side.equals("right")){
            while (true) {
                if (temp.right == null) {
                    temp.right = new BinaryTree(noOfCases);
                    return;
                }
                temp = temp.right;
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder s=new StringBuilder();
        if(left!=null)s.append(left).append(" , ");
        s.append(root).append("\t");
        if(right!=null)s.append(right).append("\t");
        return s+"";
    }
    public String preOrder(){
        StringBuilder s=new StringBuilder();
        s.append(root);
        if(left!=null)s.append(left).append(", ");
        if(right!=null)s.append(right);
        return s+"";
    }
    public String postOrder(){
        StringBuilder s=new StringBuilder();
        if(left!=null)s.append(left).append(", ");
        if(right!=null)s.append(right);
        s.append(root);
        return s+"";
    }
    public int size(){
        return 1+(left!=null? left.size() : 0)+(right!=null? right.size() : 0);
    }
    public boolean search(Object data){
        String flag=toString();
        return flag.contains(data.toString());
    }
}

