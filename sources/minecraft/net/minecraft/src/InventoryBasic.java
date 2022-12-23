package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

public class InventoryBasic
    implements IInventory
{

    public InventoryBasic(String s, int i)
    {
        field_20072_a = s;
        field_20071_b = i;
        field_20074_c = new ItemStack[i];
    }

    public ItemStack getStackInSlot(int i)
    {
        return field_20074_c[i];
    }

    public ItemStack decrStackSize(int i, int j)
    {
        if(field_20074_c[i] != null)
        {
            if(field_20074_c[i].stackSize <= j)
            {
                ItemStack itemstack = field_20074_c[i];
                field_20074_c[i] = null;
                onInventoryChanged();
                return itemstack;
            }
            ItemStack itemstack1 = field_20074_c[i].splitStack(j);
            if(field_20074_c[i].stackSize == 0)
            {
                field_20074_c[i] = null;
            }
            onInventoryChanged();
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        field_20074_c[i] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
        onInventoryChanged();
    }

    public int getSizeInventory()
    {
        return field_20071_b;
    }

    public String getInvName()
    {
        return field_20072_a;
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public void onInventoryChanged()
    {
        if(field_20073_d != null)
        {
            for(int i = 0; i < field_20073_d.size(); i++)
            {
                ((IInvBasic)field_20073_d.get(i)).func_20134_a(this);
            }

        }
    }

    public boolean func_20070_a_(EntityPlayer entityplayer)
    {
        return true;
    }

    private String field_20072_a;
    private int field_20071_b;
    private ItemStack field_20074_c[];
    private List field_20073_d;
}
