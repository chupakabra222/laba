package functions;

public class FunctionPoint {
    public FunctionPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public FunctionPoint(FunctionPoint point)
    {
        x = point.x;
        y = point.y;
    }
    public FunctionPoint()
    {
        x = y = 0;
    }
    double x, y;
}
