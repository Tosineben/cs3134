//Alden Quimby
//02.16.11

//should say implements Stack but whatever
public class ArrayStack {

	private Object[] values;
	private int count;

	public ArrayStack(int maxsize) {
		this.values = new Object[maxsize];
		count = 0;
	}

	public boolean isEmpty() {
		for (int i = 0; i < this.values.length; i++)
			if (!(this.values[i] == null)) return false;
		return true;
	}

	public void push (Object d) {
		if (count == values.length) throw new ArrayIndexOutOfBoundsException("Stack is full.");
		for (int i = values.length-2; i >= 0; i--)
			this.values[i+1] = this.values[i];
		this.values[0] = d;
		count++;
	}

	public Object top() { return this.values[0]; }

	public Object pop() {
		Object result = this.top();
		if (!this.isEmpty()) {
			for (int i = 0; i < values.length - 1; i++) values[i] = values[i+1];
			count--;
		}
		return result;
	}

	public String toString() {
		String s = "Stack:";
		for (int i = 0; i < count; i++) {
			if (i == this.count-1) s += this.values[i] + ":";
			else s += this.values[i] + " ";
		}
		return s;
	}

}