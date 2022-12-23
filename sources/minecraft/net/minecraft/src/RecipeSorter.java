package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Comparator;

class RecipeSorter
    implements Comparator
{

    RecipeSorter(CraftingManager craftingmanager)
    {
        craftingManager = craftingmanager;
    }

    public int compareRecipes(CraftingRecipe craftingrecipe, CraftingRecipe craftingrecipe1)
    {
        if(craftingrecipe1.getRecipeSize() < craftingrecipe.getRecipeSize())
        {
            return -1;
        }
        return craftingrecipe1.getRecipeSize() <= craftingrecipe.getRecipeSize() ? 0 : 1;
    }

    public int compare(Object obj, Object obj1)
    {
        return compareRecipes((CraftingRecipe)obj, (CraftingRecipe)obj1);
    }

    final CraftingManager craftingManager; /* synthetic field */
}
