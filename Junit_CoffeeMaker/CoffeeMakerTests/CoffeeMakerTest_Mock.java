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
 * Modifications
 * 20171113 - Michael W. Whalen - Extended with additional recipe.
 * 20171114 - Ian J. De Silva   - Updated to JUnit 4; fixed variable names.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;



/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Sarah Heckman
 *
 * Extended by Mike Whalen
 */

public class CoffeeMakerTest {
	
	//-----------------------------------------------------------------------
	//	DATA MEMBERS
	//-----------------------------------------------------------------------
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	
	private Recipe [] stubRecipies; 
	
	private Inventory inventory;
	
	/**
	 * The coffee maker -- our object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	/**
	 * The stubbed recipe book.
	 */
	private RecipeBook recipeBookStub;
	
	
	//-----------------------------------------------------------------------
	//	Set-up / Tear-down
	//-----------------------------------------------------------------------
	/**
	 * Initializes some recipes to test with, creates the {@link CoffeeMaker} 
	 * object we wish to test, and stubs the {@link RecipeBook}. 
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		
		recipeBookStub = mock(RecipeBook.class);
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		
		inventory = new Inventory();
		//Set up for recipe1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for recipe2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for recipe3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for recipe4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		//Set up for recipe5 (added by MWW)
		recipe5 = new Recipe();
		recipe5.setName("Super Hot Chocolate");
		recipe5.setAmtChocolate("6");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("100");

		stubRecipies = new Recipe [] {recipe1, recipe2, recipe3};
	}
	
	
	//-----------------------------------------------------------------------
	//	Test Methods
	//-----------------------------------------------------------------------
	
	// put your tests here!
	
	@Test
	public void testGetRecipes() {
		
		
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		
		Recipe [] lst =  recipeBookStub.getRecipes();
		
		for (int i =0; i < 3; i++) {
			
			assertTrue(lst[i].equals(stubRecipies[i]));
			
		}
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
	
	@Test
	public void testInventoryUsedAfterMakeCoffee() {

		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		coffeeMaker.makeCoffee(0, 100);
		String OutPut = coffeeMaker.checkInventory();
		
        // Expected output
        String expectedOutput = "Coffee: " + (15 - 3) + "\n"; 
        expectedOutput += "Milk: " + (15 - 1) + "\n"; 
        expectedOutput += "Sugar: " + (15 - 1) + "\n"; 
        expectedOutput += "Chocolate: " + (15 - 0) + "\n";
		/*
        String expectedOutput = "Coffee: " + (inventory.getCoffee() - recipe1.getAmtCoffee()) + "\n"; 
        expectedOutput += "Milk: " + (inventory.getMilk() - recipe1.getAmtMilk()) + "\n"; 
        expectedOutput += "Sugar: " + (inventory.getSugar() - recipe1.getAmtSugar()) + "\n"; 
        expectedOutput += "Chocolate: " + (inventory.getChocolate() - recipe1.getAmtChocolate()) + "\n"; 
        */
           
        // Assert the output
        assertEquals(expectedOutput, OutPut);
	}
	
	@Test
	public void testMakeCoffeValidRecipeValidExactAmt() {
		
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		assertEquals(coffeeMaker.makeCoffee(0,stubRecipies[0].getPrice()),0);
	}
	
	@Test
	public void testMakeCoffeInValidRecipeValidExactAmt() {
		
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		int amtpay = 100;
		assertEquals(coffeeMaker.makeCoffee(-1,amtpay),amtpay);
	}
	
	@Test
	public void testMakeCoffeValidRecipeInValidExactAmt() {
		
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		int payamt = 50;
		assertEquals(coffeeMaker.makeCoffee(2,payamt),payamt);
	}
	
	@Test
	public void testMakeCoffeValidRecipeAmountHigh() {
		
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		int payamt = 90;
		assertEquals(coffeeMaker.makeCoffee(0,payamt),
				payamt - stubRecipies[0].getPrice());
	}
	
	@Test
	public void testMakeCoffeinValidRecipeAmountLow() {
		
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		int payamt = 30;
		assertEquals(coffeeMaker.makeCoffee(0,payamt),payamt);
	}

	
}
