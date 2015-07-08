package pattern.singleten;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * 单例模式：Singleton的作用是保证在应用程序中，一个类Class只有一个实例存在。并提供全局访问。
 *  
 * 私有的构造方法 
 * 指向自己实例的私有静态引用
 * 以自己实例为返回值的静态的公有的方法
 * 
 * @author zhangyong
 * 
 * 优点：减少系统、内存开销，避免资源的多重占用；限制全局资源的访问；
 * 缺点：不利于扩展和测试，与单一责任原则有冲突
 *
 */
public class Singleten {

}

//懒汉模式
class Singleten1 {
	// 限制产生多个对象
	private Singleten1() {
	}

	private static final Singleten1 instance = new Singleten1();

	// 通过静态方法获取实例
	public static Singleten1 getInstance() {
		return instance;
	}

	// 实例的其他行为，尽量也是static
	public static void doSomething() {
	}
}
//饿汉模式
class Singleten2 {
	private static Singleten2 instance = null;

	private Singleten2() {

	}

	public static synchronized Singleten2 getInsetance() {
		if (null == instance) {
			instance = new Singleten2();
		}
		return instance;
	}

}
//单例升级版，产生固定数量的单例
class Singletan3{
	private static final int maxNum = 3;
	private static List<Singletan3> singletan3s = new ArrayList<Singletan3>();
	private Singletan3(){
		
	}
	static{
		for(int i=0;i<maxNum;i++){
			singletan3s.add(new Singletan3());
		}
	}
	
	public static Singletan3 getInstance(){
		Random r = new Random();
		return singletan3s.get(r.nextInt(maxNum));
	}
}



