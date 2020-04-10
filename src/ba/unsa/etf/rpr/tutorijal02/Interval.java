package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    double startPoint;
    double endPoint;
    boolean startPointIncluded;
    boolean endPointIncluded;

    public Interval(double startPoint, double endPoint, boolean startPointInInterval, boolean endPointInInterval) throws IllegalArgumentException {
        if (endPoint < startPoint) throw new IllegalArgumentException("End point is less than start point.");
        this.endPoint = endPoint;
        this.startPoint = startPoint;
        this.startPointIncluded = startPointInInterval;
        this.endPointIncluded = endPointInInterval;
    }
    public Interval () {
        this.startPoint = 0;
        this.endPoint = 0;
        this.startPointIncluded = false;
        this.endPointIncluded = false;
    }

    public static Interval intersect(Interval i1, Interval i2) {
        if (i2.isIn(i1.startPoint) && i2.isIn(i1.endPoint)) return i1;
        else if (i1.isIn(i2.startPoint) && i1.isIn(i2.endPoint)) return i2;
        else if (i2.isIn(i1.startPoint) && !i2.isIn(i1.endPoint)) {
            return new Interval(i1.startPoint, i2.endPoint, i1.startPointIncluded, i2.endPointIncluded);
        } else if (!i2.isIn(i1.startPoint) && i2.isIn(i1.endPoint)) {
            return new Interval(i2.startPoint, i1.endPoint, i2.startPointIncluded, i1.endPointIncluded);
        } else return new Interval();
    }

    public boolean isNull () {
        if (startPoint == 0 && endPoint == 0 && !startPointIncluded && !endPointIncluded) return true;
        return false;
    }


    public boolean isIn(double v) {
        if (((this.startPointIncluded && v >= this.startPoint) || (!this.startPointIncluded && v > this.startPoint))
        && ((this.endPointIncluded && v <= this.endPoint) || (!this.endPointIncluded && v < this.endPoint)))
            return  true;
        return false;
    }

    public Interval intersect(Interval interval) {
        if (this.isIn(interval.startPoint) && this.isIn(interval.endPoint)) return interval;
        if (interval.isIn(this.startPoint) && interval.isIn(this.endPoint)) return this;
        else if (this.isIn(interval.startPoint) && !this.isIn(interval.endPoint)) {
            return new Interval(interval.startPoint, this.endPoint, interval.startPointIncluded, this.endPointIncluded);
        } else if (!this.isIn(interval.startPoint) && this.isIn(interval.endPoint)) {
            return new Interval(this.startPoint, interval.endPoint, this.startPointIncluded, interval.endPointIncluded);
        } else return new Interval();
    }

    @Override
    public String toString() {
        if (this.equals(new Interval())) return "()";
        if (this.startPointIncluded)
        {
            if (this.endPointIncluded)
                return "[" + this.startPoint + "," + this.endPoint + "]";
            else return "[" + this.startPoint + "," + this.endPoint + ")";
        } else {
            if (this.endPointIncluded)
                return "(" + this.startPoint + "," + this.endPoint + "]";
            else return "(" + this.startPoint + "," + this.endPoint + ")";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Interval)) return false;
        Interval inter = (Interval) o;
        if (this.startPoint == inter.startPoint && this.endPoint == inter.endPoint
                && this.endPointIncluded == inter.endPointIncluded && this.startPointIncluded == inter.startPointIncluded)
            return true;
        return false;
    }
}
