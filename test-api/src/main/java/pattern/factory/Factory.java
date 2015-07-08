package pattern.factory;

/**
 * 工厂模式
 * 工厂模式根据抽象程度的不同分为三种：简单工厂模式（也叫静态工厂模式）、工厂方法模式、以及抽象工厂模式。
 * 
 * @author zhangyong
 * 
 * 可以使代码结构清晰，有效地封装变化。
 * 对调用者屏蔽具体的产品类
 * 用于分离产品的生产者和消费者，降低耦合度。
 * 
 */
public class Factory {

}

interface Ifactory{
	//工厂方法模式的核心，与调用者直接交互用来提供产品。
	//参数可以是string，枚举，class等等
	public <T extends Iproduct> T produce(Class<T> clazz);
}

interface IaFactory{
	//抽象工厂模式的核心，与调用者直接交互用来提供产品。
	//参数可以是string，枚举，class等等
	public <T extends Aproduct> T produceA(Class<T> clazz);
	
	public <T extends Bproduct> T produceB(Class<T> clazz);
}



interface Iproduct {
	//定义产品的规范
	public void show();
}

abstract class Aproduct implements Iproduct{
	//用于处理A类产品中共同的部分
	public void common(){
		System.out.println("独属于A类产品共有特性");
	}
}

abstract class Bproduct implements Iproduct{
	//用于处理B类产品中共同的部分
	public void common(){
		System.out.println("独属于B类产品共有特性");
	}
}


class Product1 implements Iproduct {
	//对于产品规范的一种实现
	@Override
	public void show() {
		System.out.println("这是商品一号");
	}

}

class Product2 implements Iproduct {
	//对于产品规范的一种实现
	@Override
	public void show() {
		System.out.println("这是商品二号");
	}

}


class ProductA1 extends Aproduct {
	//对于产品规范的一种实现
	@Override
	public void show() {
		System.out.println("这是A类商品一号");
	}

}

class ProductA2 extends Aproduct {
	//对于产品规范的一种实现
	@Override
	public void show() {
		System.out.println("这是A类商品二号");
	}

}

class ProductB1 extends Bproduct {
	//对于产品规范的一种实现
	@Override
	public void show() {
		System.out.println("这是B类商品一号");
	}

}

class ProductB2 extends Bproduct {
	//对于产品规范的一种实现
	@Override
	public void show() {
		System.out.println("这是B类商品二号");
	}

}

/**
 * 简单工厂
 * 要素：静态方法，产品接口，产品实现
 * @author zhangyong
 * 优点：可以根据外界给定的信息去创建需要的实例
 * 缺点：高内聚，不利于扩展，违反单一责任原则和开闭原则
 */
class SimpleFactory {
	public static Iproduct produce(String flag){
		if("one".equals(flag)){
			return new Product1();
		}else if("two".equals(flag)){
			return new Product2();
		}else{
			throw new IllegalArgumentException();
		}
	}
}

/**
 * 工厂方法模式
 * 要素：工厂接口或者工厂抽象类，工厂实现，产品接口，产品实现
 * @author zhangyong
 *
 */
class MethodFactory implements Ifactory{

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Iproduct> T produce(Class<T> clazz) {
		Iproduct product = null;
		
		try {
			product = (Iproduct) Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (T) product;
	}
}

/**
 * 抽象工厂模式
 * @author zhangyong
 *
 */
class AbstractFactory implements IaFactory{

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Aproduct> T produceA(Class<T> clazz) {
		Aproduct ap = null;
		try {
			ap = (Aproduct) Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (T)ap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Bproduct> T produceB(Class<T> clazz) {
		Bproduct ap = null;
		try {
			ap = (Bproduct) Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (T)ap;
	}
	
}
