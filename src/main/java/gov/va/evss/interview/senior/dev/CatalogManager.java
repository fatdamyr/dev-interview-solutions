package gov.va.evss.interview.senior.dev;

/**
 * The Interface CatalogManager is the start of a library that can be used for managing a catalog of items for sale. The library will
 * allow for adding, removing and modifying the quantity of items as well as retrieving the list of items and their current quantity.
 */
public interface CatalogManager {

	/**
	 * Add a new item to the catalog.
	 * 
	 * The name of the item must be unique within the catalog. All String values are acceptable; null is not allowed. The initial
	 * quantity is fully available, and must not be negative.
	 * 
	 * @param item Name of the item
	 * @param qty Initial quantity of this item
	 * @return true if the item was successfully added; false otherwise (if the quantity is negative, regardless of item name
	 *         specified)
	 * @throws CatalogItemException if the item already exists in the catalog, or its name is null
	 */
	boolean addItem(String item, int qty) throws CatalogItemException;

	/**
	 * Remove an item from the catalog.
	 * 
	 * @param item Name of the item
	 * @return true if the item was successfully removed;
	 * @throws CatalogItemException if the specified item does not exist
	 */
	boolean removeItem(String item) throws CatalogItemException;

	/**
	 * Retrieve a list of all items.
	 * 
	 * @return a String array of item names, one item per element. If there are no items in the catalog, the array has zero elements.
	 *         The sequence of elements is unspecified, and may vary by implementation.
	 */
	String[] getItems();

	/**
	 * Retrieve the current total quantity of an item in catalog.
	 * 
	 * @param item Name of the item
	 * @return the total quantity
	 * @throws CatalogItemException if the specified item does not exist in the catalog
	 */
	int getItemTotalQuantity(String item) throws CatalogItemException;

	/**
	 * Modify the quantity of an item in catalog.
	 * 
	 * Increases or decreases the quantity of the item by the amount specified. The quantity cannot become negative, if this method is
	 * called attempting to make the quantity negative no change should occur.
	 * 
	 * @param item Name of the item
	 * @param change Amount to increase/decrease available quantity
	 * @return true if the modification was successful; false otherwise (if there is insufficient quantity, you cannot have a
	 *         resulting negative quantity don't change quantity and return false)
	 * @throws CatalogItemException if the specified item does not exist in the catalog
	 */
	boolean modifyItemQuantity(String item, int change) throws CatalogItemException;

}
