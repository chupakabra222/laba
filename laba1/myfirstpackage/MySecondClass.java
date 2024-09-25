package myfirstpackage;
public class MySecondClass
{
    int i, j;
    public int GetI() {return i;}
    public int GetJ() {return j;}
    public void SetI(int i) {this.i = i;}
    public void SetJ(int j) {this.j = j;}
    public MySecondClass()
    {
        i = j = 102;
    }
    public int Action()
    {
        return j - (int)(Math.random() * i * 5);
    }
}
