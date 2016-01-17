package model;

public class Book extends Item {

	private String bookCategory;
	private String publication;
	

		public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}



	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	

		@Override
		public void displayItemDetails() {
			// TODO Auto-generated method stub
			
		}
		
}

