package edu.gatech.coffeecart.DAL.Models;

public class Item {
	  private long id;
	  private String item;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getComment() {
	    return item;
	  }

	  public void setComment(String comment) {
	    this.item = comment;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return item;
	  }
}
