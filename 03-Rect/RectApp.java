public class RectApp {
	public static void main (String[] args) {
		Rect r1 = new Rect(1,1, 10,10);
		r1.print();
		r1.drag(3, 5);
		r1.print();
	}
}
class Rect {
	int x, y;
	int w, h;
	Rect (int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	void print(){
		System.out.format("Retângulo de tamanho (%d,%d) e área (%d) na posição (%d,%d).\n",
				this.w, this.h, this.area(), this.x, this.y);
	}
	int area(){
		return this.w * this.h;
	}
	void drag(int dx, int dy){
		this.x += dx;
		this.y += dy;
	}
}
