package com.ccsi;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root=buildTreeTopDown();
        List<List<TreeNode>> result=new ArrayList<>();
        result=findAllPath(root);
        System.out.println(result.size());
    }
    //backtracking
    public static void bt(List<List<TreeNode>> bag,Stack<TreeNode> path,TreeNode curr){
        //因为压栈是做过检查，不可能出现curr==null的情况，所以不要做判断
        path.push(curr);
        if(curr.left==null&&curr.right==null){
            bag.add(new ArrayList<>(path));
        }else{
            if(curr.left!=null)bt(bag,path,curr.left);
            if(curr.right!=null)bt(bag,path,curr.right);
        }
        path.pop();
    }
    //利用loop来实现backtracking
    public static List<List<TreeNode>> findAllPath(TreeNode root){
        List<List<TreeNode>> result=new LinkedList<>();   //返回用的result
        List<TreeNode> init=new LinkedList<>();           //起始path
        //准备两个queue，一个存node，一个存path
        Queue<List<TreeNode>> pathQueue=new LinkedList<>();
        Queue<TreeNode> nodeQueue=new LinkedList<>();

        init.add(root);
        //node和起始path offer进queue
        nodeQueue.offer(root);
        pathQueue.offer(init);
        //BFS相通的步骤
        while(!nodeQueue.isEmpty()){
            List<TreeNode> path=pathQueue.poll();    //都poll（）出来
            TreeNode temp=nodeQueue.poll();

            if(temp.left==null&&temp.right==null){   //如果到叶子节点了，也就是找到路径了，做点什么事呢？
                result.add(path);
            }else{
                if(temp.left!=null){                 //如果没结束，那么要更新path
                    List<TreeNode> newPath=new LinkedList<>(path);
                    newPath.add(temp.left);          //更新path
                    pathQueue.offer(newPath);        //再进queue
                    nodeQueue.offer(temp.left);
                }
                if(temp.right!=null){                //同上
                    List<TreeNode> newPath=new LinkedList<>(path);
                    newPath.add(temp.right);
                    pathQueue.offer(newPath);
                    nodeQueue.offer(temp.right);
                }
            }
        }
        return result;
    }
    //BFS
    public static void BFS(TreeNode root){
        if(root==null)return;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode temp=queue.poll();
            System.out.println(temp.val);

            if(temp.left!=null)queue.offer(temp.left);
            if(temp.right!=null)queue.offer(temp.right);
        }
    }
    //recursion DFS
    public static void DFS(TreeNode curr){
        if(curr==null)return;
        System.out.println(curr.val);    //pre-order DFS
        if(curr.left!=null)DFS(curr.left);
        //System.out.println(curr.val);  //in-order DFS
        if(curr.right!=null)DFS(curr.right);
        //System.out.println(curr.val);  //post-order DFS
    }
    //Loop DFS 写法与BFS基本一样，用stack替代了queue，并且right和left颠倒了位置
    public static void loopDFS(TreeNode root){
        if(root==null)return;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp=stack.pop();
            System.out.println(temp.val);
            if(temp.right!=null)stack.push(temp.right); //pre-order right 和 left颠倒了位置
            if(temp.left!=null)stack.push(temp.left);

        }
    }
    public static TreeNode buildTreeTopDown(){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
        return root;
    }
    public static TreeNode buildTreeBottomUp(){
        TreeNode n4=new TreeNode(4);
        TreeNode n5=new TreeNode(5);
        TreeNode n6=new TreeNode(6);
        TreeNode n7=new TreeNode(7);
        TreeNode n2=new TreeNode(2,n4,n5);
        TreeNode n3=new TreeNode(3,n6,n7);
        TreeNode root= new TreeNode(1,n2,n3);
        return root;
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}