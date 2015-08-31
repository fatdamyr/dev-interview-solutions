package gov.va.evss.interview.senior.dev;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CatalogManagerConfig.class)
public class CatalogManager_UnitTest {

	@Autowired
	private CatalogManager catalogManager;
	
	@Before
	public void beforeTest() throws CatalogItemException{
		for(String item: catalogManager.getItems()){
			catalogManager.removeItem(item);
		}
	}
	
	@Test
	public void addItem() throws CatalogItemException{
		try{
			catalogManager.addItem(null, 0);
			Assert.fail("Exception expected");
		} catch(CatalogItemException e){
			//expected
		}
		
		//empty string allowed
		Assert.assertTrue(catalogManager.addItem("", 0));
		
		//negative qty not allowed
		Assert.assertFalse(catalogManager.addItem("item1", -1));
		
		//add 2 items
		Assert.assertTrue(catalogManager.addItem("item1", 1));
		Assert.assertTrue(catalogManager.addItem("item2", 1));
		
		//cannot add same thing 2 times
		try{
			catalogManager.addItem("item1", 1);
			Assert.fail("Exception expected");
		} catch(CatalogItemException e){
			//expected
		}
	}


	@Test
	public void  removeItem() throws CatalogItemException{
		//item not in catalog
		try{
			catalogManager.removeItem("item1");
			Assert.fail("Exception expected");
		} catch(CatalogItemException e){
			//expected
		}
		
		//quick add and remove should work
		catalogManager.addItem("item1", 1);
		Assert.assertTrue(catalogManager.removeItem("item1"));
		
		//assert remove really removed it and it isn't there
		try{
			Assert.assertTrue(catalogManager.removeItem("item1"));
			Assert.fail("Exception expected");
		} catch(CatalogItemException e){
			//expected
		}
		Assert.assertFalse(Arrays.asList(catalogManager.getItems()).contains("item1"));
	}


	@Test
	public void getItems() throws CatalogItemException{
		Assert.assertEquals(0,catalogManager.getItems().length);
		catalogManager.addItem("item1", 1);
		Assert.assertEquals(1,catalogManager.getItems().length);
		Assert.assertEquals("item1",catalogManager.getItems()[0]);
		catalogManager.addItem("item2", 2);
		Assert.assertEquals(2,catalogManager.getItems().length);
		Assert.assertTrue(Arrays.asList(catalogManager.getItems()).contains("item1"));
		Assert.assertTrue(Arrays.asList(catalogManager.getItems()).contains("item2"));
		
	}

	@Test
	public void  getItemTotalQuantity() throws CatalogItemException{
		//item not in catalog
		try{
			catalogManager.removeItem("item1");
			Assert.fail("Exception expected");
		} catch(CatalogItemException e){
			//expected
		}
		
		//get items that are in catalog
		catalogManager.addItem("item1", 1);
		catalogManager.addItem("item2", 2);
		Assert.assertEquals(1,catalogManager.getItemTotalQuantity("item1"));
		Assert.assertEquals(2,catalogManager.getItemTotalQuantity("item2"));
	}
	
	@Test
	public void modifyItemQuantity() throws CatalogItemException{
		//item not in catalog
		try{
			catalogManager.modifyItemQuantity("item1", 1);
			Assert.fail("Exception expected");
		} catch(CatalogItemException e){
			//expected
		}
		
		//2 items with initial quantity, make basic adds to quantity
		Assert.assertTrue(catalogManager.addItem("item1", 0));
		Assert.assertTrue(catalogManager.addItem("item2", 1));
		Assert.assertTrue(catalogManager.modifyItemQuantity("item1", 1));
		Assert.assertTrue(catalogManager.modifyItemQuantity("item2", 1));
		Assert.assertEquals(1,catalogManager.getItemTotalQuantity("item1"));
		Assert.assertEquals(2,catalogManager.getItemTotalQuantity("item2"));
		
		//reduce quantity, but keep at 0 (which is permitted) or above
		Assert.assertTrue(catalogManager.modifyItemQuantity("item1", -1));
		Assert.assertTrue(catalogManager.modifyItemQuantity("item2", -1));
		Assert.assertEquals(0,catalogManager.getItemTotalQuantity("item1"));
		Assert.assertEquals(1,catalogManager.getItemTotalQuantity("item2"));
		
		//try to go below 0, which isn't allowed
		Assert.assertFalse(catalogManager.modifyItemQuantity("item1", -1));
	}
}
