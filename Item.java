	public enum Item{
		WATER("water", 150), SODA("cola", 250), SNACK("twix", 350);
		private int price;
		private String name;
		
		
		public int getPrice() {
			return price;
		}
		public String getName() {
			return name;
		}
		
		
		private Item(String name,int price){
			this.price=price;
			this.name=name;
		}
	}
	
	
