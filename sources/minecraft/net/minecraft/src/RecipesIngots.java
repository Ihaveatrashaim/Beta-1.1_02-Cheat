package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class RecipesIngots
{

    public RecipesIngots()
    {
        recipeItems = (new Object[][] {
            new Object[] {
                Block.blockGold, Item.ingotGold
            }, new Object[] {
                Block.blockSteel, Item.ingotIron
            }, new Object[] {
                Block.blockDiamond, Item.diamond
            }
        });
    }

    public void addRecipes(CraftingManager craftingmanager)
    {
        for(int i = 0; i < recipeItems.length; i++)
        {
            Block block = (Block)recipeItems[i][0];
            Item item = (Item)recipeItems[i][1];
            craftingmanager.addRecipe(new ItemStack(block), new Object[] {
                "###", "###", "###", Character.valueOf('#'), item
            });
            craftingmanager.addRecipe(new ItemStack(item, 9), new Object[] {
                "#", Character.valueOf('#'), block
            });
        }

    }

    private Object recipeItems[][];
}
