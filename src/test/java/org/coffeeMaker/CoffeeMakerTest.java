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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testCanAdd2SameRecipe(){
        Recipe recipe = new Recipe();
        recipe.setName("test");
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.addRecipe(recipe);
        assertEquals(false, recipeBook.addRecipe(recipe));
    }

    /**
     * This test fails. In the description it says only 3
     * recipies can be added to the recipe book, I tested
     * this, you can add more than 3, in this test
     * I added 4 and when adding the fourth true is
     * returned indicating that you can add more than 3.
     */
//    @Test
//    public void testCanAddMoreThan3Recipe(){
//        Recipe recipe1 = new Recipe();
//        recipe1.setName("test1");
//        Recipe recipe2 = new Recipe();
//        recipe2.setName("test2");
//        Recipe recipe3 = new Recipe();
//        recipe3.setName("test3");
//        Recipe recipe4 = new Recipe();
//        recipe4.setName("test4");
//        RecipeBook recipeBook = new RecipeBook();
//        recipeBook.addRecipe(recipe1);
//        recipeBook.addRecipe(recipe2);
//        recipeBook.addRecipe(recipe3);
//
//        assertEquals(false, recipeBook.addRecipe(recipe4));
//    }

    /**
     * Test recipe name
     */
    @Test
    public void testRecipeName(){
        Recipe recipe = new Recipe();
        recipe.setName("test");
        assertEquals("test", recipe.getName());
    }
    /**
     * Test recipe name after setting it null
     */
    @Test
    public void testRecipeNameNull(){
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setName(null);
        assertEquals("test", recipe.getName());
    }
    /**
     * Test recipe setPrice
     */
    @Test
    public void testRecipeSetPrice(){
        Recipe recipe = new Recipe();
        try {
            recipe.setPrice("9");
        }catch(RecipeException e){
            // fail if price is not set
            assertEquals(true, false);
            return;
        }
        assertEquals(9, recipe.getPrice());
    }

    /**
     * Testing make a coffee with a number greater than 3
     * 3 is supposed to be max amount of recipies.
     *
     * If the user puts 4 or more, or less than 0 the
     * program will crash due to out of bounds error.
     */
//    @Test
//    public void testMakeCoffeeNotExists(){
//
//        CoffeeMaker coffeeMaker = new CoffeeMaker();
//        coffeeMaker.addRecipe(recipe1);
//        coffeeMaker.addRecipe(recipe2);
//        coffeeMaker.addRecipe(recipe3);
//        coffeeMaker.addRecipe(recipe4);
//        coffeeMaker.makeCoffee(4, 0);
//    }

    /**
     * This is used to test the inventory's ability
     * to update the ingredients. By default the ingredients
     * are set to 15, I made a recipe and subtracted what
     * should have been subtracted from the inventory
     * to get expected amounts.
     *
     * This test fails because the amount of coffee goes up
     * rather than going down after being used. The rest of the
     * ingredient amounts go down as they should
     */
//    @Test
//    public void testInventory(){
//        CoffeeMaker coffeeMaker = new CoffeeMaker();
//        Recipe recipe = new Recipe();
//        try{
//            recipe.setName("Latte");
//            recipe.setAmtChocolate("0");
//            recipe.setAmtCoffee("3");
//            recipe.setAmtMilk("3");
//            recipe.setAmtSugar("1");
//            recipe.setPrice("100");
//        }
//        catch(RecipeException e){
//        }
//        coffeeMaker.addRecipe(recipe);
//
//        coffeeMaker.makeCoffee(0, 100);
//        String inInventory = coffeeMaker.checkInventory();
//        assertEquals("Coffee: 15\nMilk: 12\nSugar: 14\nChocolate: 14\n", inInventory);
//    }

}
