package gov.va.evss.interview.senior.dev;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class CatalogManagerImpl implements CatalogManager {

	private final HashMap<String, Integer> catalog = new HashMap<String, Integer>();

	public boolean addItem(final String item, final int qty) throws CatalogItemException {
		if (qty < 0) {
			return false;
		}
		if (null == item || catalog.containsKey(item)) {
			throw new CatalogItemException();
		}
		catalog.put(item, Integer.valueOf(qty));
		return true;
	}

	public boolean removeItem(final String item) throws CatalogItemException {
		if (!catalog.containsKey(item)) {
			throw new CatalogItemException();
		}
		catalog.remove(item);
		return true;
	}

	public String[] getItems() {
		return catalog.keySet().toArray(new String[catalog.keySet().size()]);
	}

	public int getItemTotalQuantity(final String item) throws CatalogItemException {
		if (catalog.containsKey(item)) {
			return catalog.get(item);
		}
		throw new CatalogItemException();
	}

	public boolean modifyItemQuantity(final String item, final int change) throws CatalogItemException {
		if (!catalog.containsKey(item)) {
			throw new CatalogItemException();
		}
		final int qty = catalog.get(item);
		if (qty + change >= 0) {
			catalog.put(item, Integer.valueOf(qty + change));
			return true;
		} else {
			return false;
		}
	}
}
