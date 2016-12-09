	public enum Coin{
		DZIESIECGR(10), DWADZIESCIAGR(20),PIECDZIESIATGR(50),JEDEN(100),DWA(200),PIEC(500);
		
		private int value;
		private Coin(int value){
			this.value=value;
		}
		
		public int getValue(){
			return value;
		}
	}



