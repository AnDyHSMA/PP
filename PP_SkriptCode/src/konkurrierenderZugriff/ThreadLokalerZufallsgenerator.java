package konkurrierenderZugriff;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLokalerZufallsgenerator {

	public static void main(String[] args) {
		Random r=ThreadLocalRandom.current();
		System.out.println(r.nextInt(100));
	}

}
