package itamar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CoffeeShop {
	private String name;
	private ArrayList<MenuItem> menu = new ArrayList<>();
	private ArrayList<String> orders = new ArrayList<>();
	
  public CoffeeShop(String name, MenuItem[] menu, String[] orders) {
    this.name = name;
    this.menu.addAll(Arrays.asList(menu));
    this.orders.addAll(Arrays.asList(orders));
  }
  
  public String shopName() { return this.name; }
  
	public String addOrder(String item) {
		MenuItem t = this.menu.stream().filter(i -> i.getItem().equals(item)).findFirst().orElse(null);
		if (t == null) return "This item is currently unavailable!";
		this.orders.add(item);
		return "Order added!";
	}
	
	public String fulfillOrder() 
	{
		if(this.orders.isEmpty())
		    return "All orders have been fulfilled!";
		else
		    return String.format("The %s is ready!", this.orders.remove(0));
	}
	
	public String[] listOrders() 
	{
		if(this.orders.isEmpty())
		    return new String[] {};
		else
		    return this.orders.stream().toArray(String[]::new);
	}
	
	public double dueAmount() {
		if(this.orders.isEmpty())
		    return 0d;
		else 
		   return this.orders.stream().mapToDouble(s -> this.menu.stream().filter(i -> i.getItem().equals(s)).findFirst().orElse(new MenuItem("dummy", "food", 0.0)).getPrice()).sum();
	}
	
	public String cheapestItem() 
	{
		return this.menu.stream().sorted(Comparator.comparingDouble(MenuItem::getPrice)).limit(1).toArray(MenuItem[]::new)[0].getItem();
	}
	
	public String[] drinksOnly() 
	{
		return this.menu.stream().filter(i -> i.getType().equals("drink")).map(MenuItem::getItem).toArray(String[]::new);
	}
	
	public String[] foodOnly() {
		return this.menu.stream().filter(i -> i.getType().equals("food")).map(MenuItem::getItem).toArray(String[]::new);
	}
}

class MenuItem {
	private String item;
	private String type;
	private double price;
	
	public MenuItem(String item, String type, double price) {
		this.item = item;
		this.type = type;
		this.price = price;
	}

	public String getItem() { return item; }
	public String getType() { return type; }
	public double getPrice() { return price; }
}
