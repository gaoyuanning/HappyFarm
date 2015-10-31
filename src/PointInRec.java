import java.awt.Point;
import java.awt.event.MouseEvent;


public class PointInRec {
	
public static boolean judgeInRec(MouseEvent e) {
		
		Point Q = new Point(e.getPoint());
		Point pLT = new Point(74, 440);
		Point pLB = new Point(657, 734);
		Point pRB = new Point(956, 598);
		Point pRT = new Point(369, 295);
		
	    int cnt = 4;  
	    Point[] points = new Point[]{ pLT, pLB, pRB, pRT };  
	    
	    Point P = new Point(1001, Q.y); 
	    Point p1, p2;
		int sum = 0;
		for(int j = 0; j < cnt; j++) {
			p1 = points[j];
			p2 = points[(j + 1) % cnt];
			if(check_online(p1, p2, Q)) {
				return true;
			}
			if(p1.y == p2.y) continue;
			if(Q.y > min(p1.y, p2.y) && Q.y <= max(p1.y, p2.y)) {
				if(check_two_line(Q, P, p1, p2))
					sum++;
			}
		}
		if(sum % 2 == 1) return true;
		return false;
    }

	static int eps(double x) {
		if(Math.abs(x) < 1e-4) return 0;
		if(x < 0) return -1;
		return 1;
	}
	
	static double cross(Point p0, Point p1, Point p2) {
		return (p1.x - p0.x)*(p2.y - p0.y) - (p2.x - p0.x)*(p1.y - p0.y);
	}
	
	static boolean check_online(Point p1, Point p2, Point Q) {
		if(eps(cross(p1, Q, p2)) == 0 &&
		   eps(Q.x - min(p1.x, p2.x)) >= 0 && eps(Q.x - max(p1.x, p2.x)) <= 0 &&
		   eps(Q.y - min(p1.y, p2.y)) >= 0 && eps(Q.y - max(p1.y, p2.y)) <= 0 )
			return true;
		return false;
	}
	
	static boolean check_two_line(Point a,Point b, Point c, Point d) {
		double h, i, j, k;
		h = cross(a, b, c);
		i = cross(a, b, d);
		j = cross(c, d, a);
		k = cross(c, d, b);
		return eps(h * i) <= 0 && eps(j * k) <= 0;
	}
	static int min(int x, int y) {
		return x > y ? y : x;
	}
	
	static int max(int x, int y) {
		return x > y ? x : y;
	}

}
