package TDADisjointSet;

public interface DisjointSet {
    void makeSet(int x);
    NodoDisjoint find(int x);
    void union(int x,int y);
    void initiate(int [] x);
}
