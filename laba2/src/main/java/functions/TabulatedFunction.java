package functions;

public class TabulatedFunction {
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount;
        InitPoints(pointsCount, leftX, rightX);
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        pointsCount = values.length;
        InitPoints(values, leftX, rightX);
    }

    void InitPoints(int pointsCount, double x1, double x2) {
        points = new FunctionPoint[pointsCount + 1000];
        double tabInterval = getTabInterval(x1, x2);
        double x = x1;
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(x, 0);
            x += tabInterval;
        }
    }

    void InitPoints(double[] values, double x1, double x2) {
        points = new FunctionPoint[pointsCount + 1000];
        double tabInterval = getTabInterval(x1, x2);
        double x = x1;
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(x, values[i]);
            x += tabInterval;
        }
    }

    public double getLeftDomainBorder() {
        return points[0].x;
    }

    public double getRightDomainBorder() {
        return points[getPointsCount() - 1].x;
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) return Double.NaN;

        for (int i = 0; i < pointsCount; i++)
            if (x == points[i].x) return points[i].y;

        FunctionPoint[] neighbours = FindNeighbours(x);
        double m = (neighbours[1].y - neighbours[0].y) / (neighbours[1].x - neighbours[0].x);
        return m * (x - neighbours[0].x) + neighbours[0].y;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        return points[index];
    }

    public void setPoint(int index, FunctionPoint point) {
        if (point.x >= getLeftDomainBorder() && point.x <= getRightDomainBorder())
        {
            if(Math.abs(points[index - 1].x - points[index + 1].x) <= Math.abs(point.x))
                points[index] = point;
        }
        else if(getPointX(index) == getLeftDomainBorder() && point.x <= getLeftDomainBorder())
            points[0] = point;
        else if (getPointX(index) == getRightDomainBorder() && point.x >= getRightDomainBorder())
            points[getPointsCount() - 1] = point;
    }

    public double getPointX(int index) {
        return points[index].x;
    }

    public void setPointX(int index, double x) {
        setPoint(index, new FunctionPoint(x, getPoint(index).y));
    }

    public double getPointY(int index) {
        return points[index].y;
    }

    public void setPointY(int index, double y) {
        setPoint(index, new FunctionPoint(getPoint(index).x, y));
    }

    public void deletePoint(int index) {
        if (index == pointsCount - 1) points[index] = null;
        else if(index == 0)
            System.arraycopy(points, 1, points, 0, getPointsCount());

        else {
            System.arraycopy(points, 0, points, 0, index - 1);
            System.arraycopy(points, index + 1, points, index, getPointsCount());
        }
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) {
        if (getPointsCount() + 1 == points.length)
        {
            FunctionPoint[] p = new FunctionPoint[points.length + 1000];
            System.arraycopy(points, 0, p, 0, getPointsCount());
            points = p;
        }
        if (point.x < getLeftDomainBorder())
        {
            System.arraycopy(points, 0, points, 1, getPointsCount());
            points[0] = point;
        }
        else if (point.x > getRightDomainBorder())
            points[getPointsCount()] = point;

        else for (int i = 0; i < getPointsCount(); i++)
        {
                if (point.x <= points[i].x)
                {
                    System.arraycopy(points, 0, points, 0, i - 1);
                    System.arraycopy(points, i, points, i + 1, getPointsCount());
                    points[i] = point;
                    break;
                }

            }
        pointsCount++;
    }

    double getTabInterval(double x1, double x2) {
        return Math.abs((x2 - x1 + 1) / getPointsCount());
    }

    double getTabInterval() {
        return Math.abs((getRightDomainBorder() - getLeftDomainBorder() + 1) / getPointsCount());
    }
    FunctionPoint[] FindNeighbours(double x)
    {
        double leftX = getLeftDomainBorder(), rightX = getRightDomainBorder(), tabInterval = getTabInterval();
        int leftInd = 0, rightInd = getPointsCount() - 1;
        while (leftInd < getPointsCount()) {
            leftX = getPointX(leftInd);
            if (Math.abs(x - leftX) <= tabInterval) break;
            leftInd++;
        }
        while (rightInd > 0) {
            rightX = getPointX(rightInd);
            if (Math.abs(x - rightX) <= tabInterval) break;
            rightInd--;
        }
        FunctionPoint[] neighbours = new FunctionPoint[2];
        neighbours[0] = getPoint(leftInd);
        neighbours[1] = getPoint(rightInd);
        return neighbours;
    }
    FunctionPoint[] points;
    int pointsCount;
}
