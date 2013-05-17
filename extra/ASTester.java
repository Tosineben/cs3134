
public class ASTester {
	public static void main(String[] args) {

		ArrayStack myStack = new ArrayStack(10);

		myStack.push(new Integer(4));
		myStack.push(new Integer(7));
		myStack.push(new Integer(12));
		myStack.push(new Integer(1));
		myStack.push(new Integer(9));
		Integer z = (Integer)myStack.pop();
		Integer x = (Integer)myStack.pop();
		System.out.println(myStack+ "\n" + z + "\n" + x);
	}
}