package inherit.practice2.product;

public class Tv extends Product{



	public Tv(String model, int price) {
		super(model,price);
//		this.model=model;
//		this.price=price;
	}

	@Override
	public void out() {
		System.out.println("TV");
	}


}//Tv class
