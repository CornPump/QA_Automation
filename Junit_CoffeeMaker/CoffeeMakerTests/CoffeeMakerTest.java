/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	
	private Inventory inventory;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		inventory = new Inventory();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("4");
		recipe2.setAmtMilk("2");
		recipe2.setAmtSugar("2");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
	}
	
	
	 
	 
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}
	
    // Test Recipe class here ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	@Test
    public void testSetName() {
        // Declare and initialize a new Recipe object
        Recipe recipe = new Recipe();

        // Set the name
        String testName = "Latte";
        recipe.setName(testName);

        // Assert that the name was set correctly
        assertEquals(testName, recipe.getName());
    }
	
	@Test
    public void testSetPriceInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String price = "5";
        recipe.setPrice(price);

        assertEquals(Integer.parseInt(price), recipe.getPrice());
    }
	
	@Test(expected = RecipeException.class)
    public void testSetPriceNoneInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String price = "ggwp";
        recipe.setPrice(price);
    }
	
	@Test(expected = RecipeException.class)
    public void testSetPriceNegativeInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String price = "-1";
        recipe.setPrice(price);
    }
	
	@Test
    public void testSetPriceZeroInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String price = "0";
        recipe.setPrice(price);
    }
	
	@Test
    public void testSetamtCoffeeInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtcoffe = "5";
        recipe.setAmtCoffee(amtcoffe);

        assertEquals(Integer.parseInt(amtcoffe), recipe.getAmtCoffee());
    }
	
	@Test(expected = RecipeException.class)
    public void testSetamtCoffeeNoneInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtcoffe = "ggwp";
        recipe.setAmtCoffee(amtcoffe);
    }
	
	@Test(expected = RecipeException.class)
    public void testSetamtCoffeeNegativeInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtcoffe = "-1";
        recipe.setAmtCoffee(amtcoffe);
    }
	
	@Test
    public void testSetamtCoffeZeroInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtcoffe = "0";
        recipe.setAmtCoffee(amtcoffe);
    }
    
    @Test
    public void testSetamtMilkInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtmilk = "5";
        recipe.setAmtMilk(amtmilk);

        assertEquals(Integer.parseInt(amtmilk), recipe.getAmtMilk());
    }
	
	@Test(expected = RecipeException.class)
    public void testSetamtMilkNoneInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtmilk = "ggwp";
        recipe.setAmtMilk(amtmilk);
    }
	
	@Test(expected = RecipeException.class)
    public void testSetamtMilkNegativeInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtmilk = "-1";
        recipe.setAmtMilk(amtmilk);
    }
	
	@Test
    public void testSetamtMilkZeroInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtmilk = "0";
        recipe.setAmtMilk(amtmilk);
    }
	
    @Test
    public void testSetamtSugarInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtsugar = "5";
        recipe.setAmtSugar(amtsugar);

        assertEquals(Integer.parseInt(amtsugar), recipe.getAmtSugar());
    }
	
	@Test(expected = RecipeException.class)
    public void testSetamtSugarNoneInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtsugar = "ggwp";
        recipe.setAmtSugar(amtsugar);
    }
	
	@Test(expected = RecipeException.class)
    public void testSetamtSugarNegativeInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtsugar = "-1";
        recipe.setAmtSugar(amtsugar);
    }
	
	@Test
    public void testSetamtSugarZeroInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtsugar = "0";
        recipe.setAmtSugar(amtsugar);
    }
    
    @Test
    public void testSetamtChocolateInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtchoco = "5";
        recipe.setAmtChocolate(amtchoco);

        assertEquals(Integer.parseInt(amtchoco), recipe.getAmtChocolate());
    }
	
	@Test(expected = RecipeException.class)
    public void testSetamtChocolateNoneInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtchoco = "ggwp";
        recipe.setAmtChocolate(amtchoco);
    }
	
	@Test(expected = RecipeException.class)
    public void testSetamtChocolatNegativeInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtchoco = "-1";
        recipe.setAmtChocolate(amtchoco);
    }
	
	@Test
    public void testSetamtChocolatZeroInt() throws RecipeException{

        Recipe recipe = new Recipe();

        String amtchoco = "0";
        recipe.setAmtChocolate(amtchoco);
    }
	
	@Test
    public void testRecipesAreEquals() throws RecipeException{

        Recipe recipe = new Recipe();

        //set it same as recipe1
        
      	recipe.setName("Coffee");
      	recipe.setAmtChocolate("0");
  		recipe.setAmtCoffee("3");
  		recipe.setAmtMilk("1");
      	recipe.setAmtSugar("1");
      	recipe.setPrice("50");
      	assertTrue(recipe.equals(recipe1));
    }
	
	@Test
    public void testRecipesNamesAreNotEquals() throws RecipeException{

        Recipe recipe = new Recipe();

        //set it same as recipe1
        
        recipe.setName("NotCoffee");
      	recipe.setAmtChocolate("0");
  		recipe.setAmtCoffee("3");
  		recipe.setAmtMilk("1");
      	recipe.setAmtSugar("1");
      	recipe.setPrice("50");
      	
      	// test names aren't equals
      	
      	assertFalse(recipe.equals(recipe1));
	}
	

	// Test RecipeBook class here ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
	 @Test
    // Test Get Recipe
    public void testGetRecipeNull() {
    	
    	RecipeBook rebook = new RecipeBook();	
    	assertNull(rebook.getRecipes()[0]);
    	
    }
	
	 @Test
    // Test Get Recipe
    public void testGetRecipeWithOneVal() {
    	
    	RecipeBook rebook = new RecipeBook();   	
    	rebook.addRecipe(recipe1); 
    	
    	assertTrue(rebook.getRecipes()[0].equals(recipe1));
    	
    }
	
    @Test
    // Test adding one Recipe
    public void testAddRecipeOne() {
    	
    	RecipeBook rebook = new RecipeBook();
    	boolean IsSucced = false;
    	IsSucced = rebook.addRecipe(recipe1);
    	
    	assertTrue(IsSucced);
    	
    }
    
    @Test
    // Test adding Three Recipes
    public void testAddRecipeThree() {
    	
    	RecipeBook rebook = new RecipeBook();
    	boolean IsSucced = false;
    	IsSucced = rebook.addRecipe(recipe1);
    	IsSucced = rebook.addRecipe(recipe2);
    	IsSucced = rebook.addRecipe(recipe3);
    	
    	assertTrue(IsSucced);
    	
    }
    
    @Test
    // Test adding four Recipes, should not allow as SRS defined 3 as max.
    public void testAddRecipeFour() {
    	
    	RecipeBook rebook = new RecipeBook();
    	boolean IsSucced = false;
    	IsSucced = rebook.addRecipe(recipe1);
    	IsSucced = rebook.addRecipe(recipe2);
    	IsSucced = rebook.addRecipe(recipe3);
    	IsSucced = rebook.addRecipe(recipe4);
    	
    	assertFalse(IsSucced);
    	
    }
    
    @Test
    // Test deleting one Recipe return value is correct, add one recipe first.
    public void testDeleteRecipeOneReturnValue() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	
    	String OutPut = rebook.deleteRecipe(0);
    			
    	assertEquals(OutPut,recipe1.getName());
    }
    
    @Test
    // Test deleting one Recipe return value is correct, there is no Recipe in that place.
    public void testDeleteRecipeWrongOneReturnValue() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);

    			
    	assertNull(rebook.deleteRecipe(1));
    }

    /*
	@Test
    public void testRecipesAmtChocolateAreNotEquals() throws RecipeException{

        Recipe recipe = new Recipe();
        
        //set it same as recipe1
        
      	recipe.setName("Coffee");
      	recipe.setAmtChocolate("10");
  		recipe.setAmtCoffee("3");
  		recipe.setAmtMilk("1");
      	recipe.setAmtSugar("1");
      	recipe.setPrice("50");
      	
      	// test AmtChocolate aren't equals
      	
      	assertFalse(recipe.equals(recipe1));
	}

	@Test
    public void testRecipesAmtCoffeeAreNotEquals() throws RecipeException{

        Recipe recipe = new Recipe();

        //set it same as recipe1
        
      	recipe.setName("Coffee");
      	recipe.setAmtChocolate("0");
      	recipe.setAmtCoffee("10");
  		recipe.setAmtMilk("1");
      	recipe.setAmtSugar("1");
      	recipe.setPrice("50");
      	
      	// test AmtCoffee aren't equals
      	
      	assertFalse(recipe.equals(recipe1));
	}
	
	@Test
    public void testRecipesAmtMilkAreNotEquals() throws RecipeException{

        Recipe recipe = new Recipe();

        //set it same as recipe1
        
      	recipe.setName("Coffee");
      	recipe.setAmtChocolate("0");
  		recipe.setAmtCoffee("3");
  		recipe.setAmtMilk("10");
      	recipe.setAmtSugar("1");
      	recipe.setPrice("50");
      	
      	// test AmtMilk aren't equals      	
      	assertFalse(recipe.equals(recipe1));
	}
	
	@Test
    public void testRecipessetAmtSugarAreNotEquals() throws RecipeException{

        Recipe recipe = new Recipe();

        //set it same as recipe1
        
      	recipe.setName("Coffee");
      	recipe.setAmtChocolate("0");
  		recipe.setAmtCoffee("3");
  		recipe.setAmtMilk("1");
  		recipe.setAmtSugar("10");
      	recipe.setPrice("50");
      	// test AmtSugar aren't equals
      	assertFalse(recipe.equals(recipe1));  	
	}

	@Test
    public void testRecipesPriceAreNotEquals() throws RecipeException{

        Recipe recipe = new Recipe();

        //set it same as recipe1
        
      	recipe.setName("Coffee");
      	recipe.setAmtChocolate("0");
  		recipe.setAmtCoffee("3");
  		recipe.setAmtMilk("1");
      	recipe.setAmtSugar("1");
      	
      	// test Price aren't equals
      	recipe.setPrice("100");
      	
      	assertTrue(recipe.equals(recipe1));
      	
	}
	*/
    
    @Test
    // Test deleting Recipe actually deletes the Recipe
    public void testDeleteRecipeRemovesRecipeFromRecipeBook() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	rebook.deleteRecipe(0);
    			
    	assertNull(rebook.getRecipes()[0]);
    }
    
    @Test
    // Test deleting asking a negative number
    public void testDeleteOutOfBoundNegativeNumber() {
    	
    	RecipeBook rebook = new RecipeBook();
    			
    	assertNull(rebook.deleteRecipe(-1));
    }
	
    @Test
    // Test deleting asking a big number
    public void testDeleteOutOfBoundPositiveNumber() {
    	
    	RecipeBook rebook = new RecipeBook();
    			
    	assertNull(rebook.deleteRecipe(100));
    }
    
    @Test
    // Test editing a Recipe asking a negative number
    public void testEditOutOfBoundNegativeNumber() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    			
    	assertNull(rebook.editRecipe(-1,recipe2));
    }
	
    @Test
    // Test Editing a Recipe asking a big number
    public void testEditOutOfBoundPositiveNumber() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    			
    	assertNull(rebook.editRecipe(100,recipe2));
    }
    
    @Test
    // Test Editing a Recipe asking a recipe number that's not there
    public void testEditRecipeDoesNotExist() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    			
    	assertNull(rebook.editRecipe(2,recipe2));
    }
    
    @Test
    // Test Editing a Recipe 
    public void testEditRecipeReturnValueCorrect() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	
    	String ReturnValue = rebook.editRecipe(0,recipe2);
    	
    	assertEquals(ReturnValue,recipe2.getName());
    }
    
    @Test
    // Test Editing a Recipe 
    public void testEditRecipeCorrect() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	
    	rebook.editRecipe(0,recipe2);
    	
    	assertTrue(rebook.getRecipes()[0].equals(recipe2));
    }
    
    @Test
    // Test Editing a Recipe 
    public void testEditRecipeName() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	String originName = recipe1.getName();
    	rebook.editRecipe(0,recipe2);
    	
    	assertEquals(originName,rebook.getRecipes()[0].getName());
    }
	
    @Test
    // Test Editing a Recipe 
    public void testEditRecipeAmtChocolate() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	int AmtChocolate = recipe2.getAmtChocolate();
    	rebook.editRecipe(0,recipe2);
    	
    	assertEquals(AmtChocolate,recipe2.getAmtChocolate());
    }
    
    @Test
    // Test Editing a Recipe 
    public void testEditRecipeAmtCoffee() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	int AmtCoffee = recipe2.getAmtCoffee();
    	rebook.editRecipe(0,recipe2);
    	
    	assertEquals(AmtCoffee,recipe2.getAmtCoffee());
    }
    
    @Test
    // Test Editing a Recipe 
    public void testEditRecipeAmtMilk() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	int AmtMilk = recipe2.getAmtMilk();
    	rebook.editRecipe(0,recipe2);
    	
    	assertEquals(AmtMilk,recipe2.getAmtMilk());
    }
    
    @Test
    // Test Editing a Recipe 
    public void testEditRecipeAmtSugar() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	int AmtSugar = recipe2.getAmtSugar();
    	rebook.editRecipe(0,recipe2);
    	
    	assertEquals(AmtSugar,recipe2.getAmtSugar());
    }
    
    @Test
    // Test Editing a Recipe 
    public void testEditRecipePrice() {
    	
    	RecipeBook rebook = new RecipeBook();
    	rebook.addRecipe(recipe1);
    	int Price = recipe2.getPrice();
    	rebook.editRecipe(0,recipe2);
    	
    	assertEquals(Price,recipe2.getPrice());
    }
	
    // Test Inventory class here ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	@Test
	public void testInventorySetCoffeeSuccess() {
		
		inventory.setCoffee(7);
		assertEquals(inventory.getCoffee(),7);
		
	}
	
	@Test
	public void testInventorySetCoffeeNegative() {
		
		inventory.setCoffee(-7);
		assertNotEquals(inventory.getCoffee(),-7);
		
	}
	
	@Test
	public void testInventorySetMilkSuccess() {
		
		inventory.setMilk(7);
		assertEquals(inventory.getMilk(),7);
		
	}
	
	@Test
	public void testInventorySetMilkNegative() {
		
		inventory.setMilk(-7);
		assertNotEquals(inventory.getMilk(),-7);
		
	}
	
	@Test
	public void testInventorySetChocolateSuccess() {
		
		inventory.setChocolate(7);
		assertEquals(inventory.getChocolate(),7);
		
	}
	
	@Test
	public void testInventorySetChocolateNegative() {
		
		inventory.setChocolate(-7);
		assertNotEquals(inventory.getChocolate(),-7);
		
	}
	
	@Test
	public void testInventorySetSugarSuccess() {
		
		inventory.setSugar(7);
		assertEquals(inventory.getSugar(),7);
		
	}
	
	@Test
	public void testInventorySetSugarNegative() {
		
		inventory.setSugar(-7);
		assertNotEquals(inventory.getSugar(),-7);
		
	}
	
	@Test
	public void testInventoryAddCoffeeSuccess() throws InventoryException{
		
		int pre = inventory.getCoffee();
		int add = 5;

		inventory.addCoffee(Integer.toString(add));
		
		assertEquals(inventory.getCoffee(),pre+add);
	}
	
	@Test(expected = InventoryException.class)
	public void testInventoryAddCoffeeNegative() throws InventoryException{
		
		int add = -5;

		inventory.addCoffee(Integer.toString(add));
		
	}
	
	@Test(expected = InventoryException.class)
	public void testInventoryAddCoffeeNonIntegerInput() throws InventoryException{

		inventory.addCoffee("Fail");
		
	}
	
	@Test
	public void testInventoryAddMilkSuccess() throws InventoryException {
	    int pre = inventory.getMilk();
	    int add = 5;

	    inventory.addMilk(Integer.toString(add));

	    assertEquals(inventory.getMilk(), pre + add);
	}

	@Test(expected = InventoryException.class)
	public void testInventoryAddMilkNegative() throws InventoryException {
	    int add = -5;

	    inventory.addMilk(Integer.toString(add));
	}

	@Test(expected = InventoryException.class)
	public void testInventoryAddMilkNonIntegerInput() throws InventoryException {
	    inventory.addMilk("Fail");
	}
	
	@Test
	public void testInventoryAddSugarSuccess() throws InventoryException {
	    int pre = inventory.getSugar();
	    int add = 5;

	    inventory.addSugar(Integer.toString(add));

	    assertEquals(inventory.getSugar(), pre + add);
	}

	@Test(expected = InventoryException.class)
	public void testInventoryAddSugarNegative() throws InventoryException {
	    int add = -5;

	    inventory.addSugar(Integer.toString(add));
	}

	@Test(expected = InventoryException.class)
	public void testInventoryAddSugarNonIntegerInput() throws InventoryException {
	    inventory.addSugar("Fail");
	}

	@Test
	public void testInventoryAddChocolateSuccess() throws InventoryException {
	    int pre = inventory.getChocolate();
	    int add = 5;

	    inventory.addChocolate(Integer.toString(add));

	    assertEquals(inventory.getChocolate(), pre + add);
	}

	@Test(expected = InventoryException.class)
	public void testInventoryAddChocolateNegative() throws InventoryException {
	    int add = -5;

	    inventory.addChocolate(Integer.toString(add));
	}

	@Test(expected = InventoryException.class)
	public void testInventoryAddChocolateNonIntegerInput() throws InventoryException {
	    inventory.addChocolate("Fail");
	}

	@Test
	public void testEnoughIngredientsSuccess() throws RecipeException{
		
		
		assertTrue(inventory.enoughIngredients(recipe1));
		
		Recipe recipe = new Recipe();

        //set it same as with default inventory recipe vals
        
        recipe.setName("TestLiquid");
      	recipe.setAmtChocolate(String.valueOf(inventory.getChocolate()));
  		recipe.setAmtCoffee(String.valueOf(inventory.getCoffee()));
  		recipe.setAmtMilk(String.valueOf(inventory.getMilk()));
      	recipe.setAmtSugar(String.valueOf(inventory.getSugar()));
      	recipe.setPrice("50");
      	
      	assertTrue(inventory.enoughIngredients(recipe));
		
	}
	
	@Test
	public void testEnoughIngredientsNotEnoughChocolate() throws RecipeException{
		
		
		Recipe recipe = new Recipe();
        
        recipe.setName("TestLiquid");
      	recipe.setAmtChocolate(String.valueOf(inventory.getChocolate() + 5));
  		recipe.setAmtCoffee(String.valueOf(inventory.getCoffee()));
  		recipe.setAmtMilk(String.valueOf(inventory.getMilk()));
      	recipe.setAmtSugar(String.valueOf(inventory.getSugar()));
      	recipe.setPrice("50");
      	
      	assertFalse(inventory.enoughIngredients(recipe));
	}
	
	@Test
	public void testEnoughIngredientsNotEnoughCoffe() throws RecipeException{
		
		
		Recipe recipe = new Recipe();
        
        recipe.setName("TestLiquid");
      	recipe.setAmtChocolate(String.valueOf(inventory.getChocolate()));
  		recipe.setAmtCoffee(String.valueOf(inventory.getCoffee() + 5));
  		recipe.setAmtMilk(String.valueOf(inventory.getMilk()));
      	recipe.setAmtSugar(String.valueOf(inventory.getSugar()));
      	recipe.setPrice("50");
      	
      	assertFalse(inventory.enoughIngredients(recipe));
      	
	}
	
	@Test
	public void testEnoughIngredientsNotEnoughMilk() throws RecipeException{
		
		
		Recipe recipe = new Recipe();
        
        recipe.setName("TestLiquid");
      	recipe.setAmtChocolate(String.valueOf(inventory.getChocolate()));
  		recipe.setAmtCoffee(String.valueOf(inventory.getCoffee()));
  		recipe.setAmtMilk(String.valueOf(inventory.getMilk() + 5));
      	recipe.setAmtSugar(String.valueOf(inventory.getSugar()));
      	recipe.setPrice("50");
      	
      	assertFalse(inventory.enoughIngredients(recipe));
      	
	}
	
	@Test
	public void testEnoughIngredientsNotEnoughSugar() throws RecipeException{
		
		
		Recipe recipe = new Recipe();
        
        recipe.setName("TestLiquid");
      	recipe.setAmtChocolate(String.valueOf(inventory.getChocolate()));
  		recipe.setAmtCoffee(String.valueOf(inventory.getCoffee()));
  		recipe.setAmtMilk(String.valueOf(inventory.getMilk()));
      	recipe.setAmtSugar(String.valueOf(inventory.getSugar() + 5));
      	recipe.setPrice("50");
      	
      	assertFalse(inventory.enoughIngredients(recipe));
      	
	}

	@Test
	public void testUseIngredientsUseChocolate() throws RecipeException {
		
		Recipe recipe = new Recipe();
		
		int UseAmount = 5;
		recipe.setAmtChocolate(Integer.toString(UseAmount));
		
		int PreUse = inventory.getChocolate();
		int AfterUseExcpected = PreUse - UseAmount;
		
		inventory.useIngredients(recipe);
		assertEquals(AfterUseExcpected,inventory.getChocolate());
	}
	
	@Test
	public void testUseIngredientsUseMilk() throws RecipeException {
	    Recipe recipe = new Recipe();
	    
	    int UseAmount = 5;
	    recipe.setAmtMilk(Integer.toString(UseAmount));
	    
	    int PreUse = inventory.getMilk();
	    int AfterUseExpected = PreUse - UseAmount;
	    
	    inventory.useIngredients(recipe);
	    assertEquals(AfterUseExpected, inventory.getMilk());
	}

	@Test
	public void testUseIngredientsUseSugar() throws RecipeException {
	    Recipe recipe = new Recipe();
	    
	    int UseAmount = 5;
	    recipe.setAmtSugar(Integer.toString(UseAmount));
	    
	    int PreUse = inventory.getSugar();
	    int AfterUseExpected = PreUse - UseAmount;
	    
	    inventory.useIngredients(recipe);
	    assertEquals(AfterUseExpected, inventory.getSugar());
	}
	
	@Test
	public void testUseIngredientsUseCoffee() throws RecipeException {
	    Recipe recipe = new Recipe();
	    
	    int UseAmount = 5;
	    recipe.setAmtCoffee(Integer.toString(UseAmount));
	    
	    int PreUse = inventory.getSugar();
	    int AfterUseExpected = PreUse - UseAmount;
	    
	    inventory.useIngredients(recipe);
	    assertEquals(AfterUseExpected, inventory.getCoffee());
	}

	@Test
	public void testInventoryToString() {
		
        // Expected output
        String expectedOutput = "Coffee: " + inventory.getCoffee() + "\n"; 
        expectedOutput += "Milk: " + inventory.getMilk() + "\n"; 
        expectedOutput += "Sugar: " + inventory.getSugar() + "\n"; 
        expectedOutput += "Chocolate: " + inventory.getChocolate() + "\n"; 
        
           
        // Assert the output
        assertEquals(expectedOutput, inventory.toString());
	}
	
	
    // Test CoffeeMaker class here ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
	
	
}
