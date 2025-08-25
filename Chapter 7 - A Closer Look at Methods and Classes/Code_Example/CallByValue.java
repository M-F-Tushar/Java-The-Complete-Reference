class Test
{
    int a, b;

    Test()
    {

    }

    Test(int i, int j)
    {
        a = i;
        b = j;
    }

    void math(int i, int j)
    {
        i*=2; 
        j/=2;
    }

    void math(Test o)
    {
        o.a *= 2;
        o.b /= 2;
    }
}


public class CallByValue
{
    public static void main(String[] args) 
    {
        Test ob = new Test();
        int a = 15, b = 20;
        System.out.println("a and b before call: " + a + " " + b);
        ob.math(a, b);
        System.out.println("a and b after call: " + a + " " + b);
        Test obj = new Test(15, 20);
        System.out.println("obj.a and obj.b before call: " + obj.a + " " + obj.b);
        ob.math(obj);
        System.out.println("obj.a and obj.b after call: " + obj.a + " " + obj.b);
    }
}
