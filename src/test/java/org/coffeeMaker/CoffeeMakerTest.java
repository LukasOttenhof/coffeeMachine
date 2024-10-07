/*
 * Copyright (c) 2024,
 * Adapted from Sarah Heckman, Laurie Williams and Dright Ho
 * assignment.
 */

package org.coffeeMaker;

import org.coffeeMaker.exceptions.InventoryException;
import org.coffeeMaker.exceptions.RecipeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CoffeeMaker class.
 *
 * Failed tests are:
 * testMakeCoffeeNonExistentRecipe() - due to out of bounds error
 *
 * testInventoryDecrease() - due to coffee amount going up rather than down
 *
 * testAddInventory() - due to adding sugar throwing exeption so sugar and
 * chocolate can not be updated
 *
 * testDeleteRecipe() - due to delete recipe not deleting recipes
 *
 * testAdd4recipe() - due to more than 3 recipes being allowed to be
 * added to the recipe book
 *
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {
    private CoffeeMaker coffeeMaker;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @BeforeEach
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

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
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
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
     * This method is used to test the add inventory function. This test fails because
     * when trying to add sugar an inventory exception is thrown, it says,
     * "Units of sugar must be a positive integer" Coffee and milk are updated
     * correctly, but sugar and chocolate can not be updated because of the bug
     * causing the exception.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("5", "5", "5", "5");
        String inInventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: 20\nMilk: 20\nSugar: 20\nChocolate: 20\n", inInventory);
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
    @Test
    public void testAddInventoryException() throws InventoryException {
        assertThrows(InventoryException.class, () -> {
                coffeeMaker.addInventory("4", "-1", "asdf", "3");});
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

    /**
     * This method is used to test if you can duplicate recipes.
     */
    @Test
    public void testCanAdd2SameRecipe(){
        Recipe recipe = new Recipe();
        recipe.setName("test");
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.addRecipe(recipe);
        assertFalse(recipeBook.addRecipe(recipe));
    }

    /**
     * This test is used to test if you can add more than 3 recipes.
     * In the description of the software it says only 3
     * recipes can be added to the recipe book, I tested
     * this, you can add more than 3, in this test
     * I added 4 and when adding the fourth true is
     * returned indicating it was added successfuly.
     */
    @Test
    public void testAdd4recipe(){
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        recipeBook.addRecipe(recipe3);
        assertEquals(false, recipeBook.addRecipe(recipe4));
    }

    /**
     * Testing that you can add 3 recipes and that they
     * can be retrieved from the coffee maker.
     */
    @Test
    public void testAdd3Recipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        Recipe[] recipes = coffeeMaker.getRecipes();
        assertEquals(recipes[0], recipe1);
        assertEquals(recipes[1], recipe2);
        assertEquals(recipes[2], recipe3);
    }

    /**
     * Used to ensure setName function works correctly.
     */
    @Test
    public void testRecipeName(){
        Recipe recipe = new Recipe();
        recipe.setName("test");
        assertEquals("test", recipe.getName());
    }
    /**
     * Test recipe setPrice. If exception is thrown then the test will fail.
     */
    @Test
    public void testRecipeSetPrice(){
        Recipe recipe = new Recipe();
        try {
            recipe.setPrice("9");
        }
        catch(RecipeException e){
            fail();
            return;
        }
        assertEquals(9, recipe.getPrice());
    }

    /**
     * Testing make a coffee with a number greater than 3
     * 3 is supposed to be max amount of recipes.
     *
     * If the user enters 4 or more, or less than 0 the
     * program will crash due to out of bounds error.
     */
    @Test
    public void testMakeCoffeeNonExistentRecipe(){
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        coffeeMaker.addRecipe(recipe4);
        coffeeMaker.makeCoffee(4, 0);
    }

    /**
     * This is used to test the inventory's ability
     * to update the ingredients as they are used. By default, the ingredients
     * are set to 15, I made a recipe and subtracted what
     * should have been subtracted from the inventory
     * to get expected amounts.
     *
     * This test fails because the amount of coffee goes up
     * rather than going down after being used. The rest of the
     * ingredient amounts go down as they should
     */
    @Test
    public void testInventoryDecrease(){
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        Recipe recipe = new Recipe();
        try{
            recipe.setName("Latte");
            recipe.setAmtChocolate("0");
            recipe.setAmtCoffee("3");
            recipe.setAmtMilk("3");
            recipe.setAmtSugar("1");
            recipe.setPrice("100");
        }
        catch(RecipeException e){
            fail();
        }
        coffeeMaker.addRecipe(recipe);

        coffeeMaker.makeCoffee(0, 100);
        String inInventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: 15\nMilk: 12\nSugar: 14\nChocolate: 14\n", inInventory);
    }

    /**
     * This test is used to test the default inventory which should be
     * 15 of each item
     */
    @Test
    public void testDefaultInventory(){
        assertEquals(coffeeMaker.checkInventory(), "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n");
    }



    /**
     * This test exposes a bug that happens when you first add a recipe, then delete it.
     * Even though the recipe is deleted you can still make that recipe, and edit it,
     * this is because it is never really deleted. This is shown in the test as when
     * trying to assert null the test fails because "coffee" is returned.
     */
    @Test
    public void testDeleteRecipe(){
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.deleteRecipe(1);
        Recipe[] recipies = coffeeMaker.getRecipes();
        assertNull(recipies[0]);

    }

    /**
     * this test tries to buy coffee it doesn't have enough money for
     * to ensure the correct amount of change is returned
     */
    @Test
    public void testChange(){
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);

        assertEquals(3, coffeeMaker.makeCoffee(1, 3));
        assertEquals(3, coffeeMaker.makeCoffee(3, 3));
    }

    /**
     * This method makes sure that inventory is not changed
     * after a customer tries to buy a coffee but is unable
     * to due to lack of money. I made a 2 coffee makers
     * to compare their inventories before and after the failed
     * purchase
     */
    @Test void testInventoryChangeNotPurchased(){
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        CoffeeMaker coffeeMaker2 = new CoffeeMaker();
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.makeCoffee(1, 3);
        assertEquals(coffeeMaker.checkInventory(), coffeeMaker2.checkInventory());
    }

}
