package codewars;

public class TenMinWalk {

	public TenMinWalk() {
		// TODO Auto-generated constructor stub
	}

	public static boolean isValid(char[] walk) {
		// Insert brilliant code here
		if (!(walk.length == 10)) {
			return false;
		}
		int nsPosition = 0;
		int ewPosition = 0;
		for (int i = 0; i < 10; i++) {
			char currCh = walk[i];
			switch (currCh) {
			case 'n':
				nsPosition++;
				break;
			case 's':
				nsPosition--;
				break;
			case 'e':
				ewPosition++;
				break;
			case 'w':
				ewPosition--;
				break;
			}
		}
		return (nsPosition == 0 && ewPosition == 0);
	}

	public static void main(String[] args) {

		System.out.println(isValid(new char[] { 'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's' }));
		System.out.println(isValid(new char[] { 'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e' }));
		System.out.println(isValid(new char[] { 'w' }));
		System.out.println(isValid(new char[] { 'n', 'n', 'n', 's', 'n', 's', 'n', 's', 'n', 's' }));

	}

}
