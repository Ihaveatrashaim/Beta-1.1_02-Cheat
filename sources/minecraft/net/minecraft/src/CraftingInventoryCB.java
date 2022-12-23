package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.*;

public abstract class CraftingInventoryCB
{

    public CraftingInventoryCB()
    {
        field_20123_d = new ArrayList();
        field_20122_e = new ArrayList();
        unusedList = 0;
        craftMatrix = 0;
        field_20121_g = new ArrayList();
        craftResult = new HashSet();
    }

    protected void func_20117_a(Slot slot)
    {
        slot.field_20007_a = field_20122_e.size();
        field_20122_e.add(slot);
        field_20123_d.add(null);
    }

    public void func_20114_a()
    {
        for(int i = 0; i < field_20122_e.size(); i++)
        {
            ItemStack itemstack = ((Slot)field_20122_e.get(i)).getStack();
            ItemStack itemstack1 = (ItemStack)field_20123_d.get(i);
            if(ItemStack.func_20107_a(itemstack1, itemstack))
            {
                continue;
            }
            itemstack1 = itemstack != null ? itemstack.copy() : null;
            field_20123_d.set(i, itemstack1);
            for(int j = 0; j < field_20121_g.size(); j++)
            {
                ((ICrafting)field_20121_g.get(j)).func_20159_a(this, i, itemstack1);
            }

        }

    }

    public Slot func_20118_a(int i)
    {
        return (Slot)field_20122_e.get(i);
    }

    public ItemStack func_20116_a(int i, int j, EntityPlayer entityplayer)
    {
        ItemStack itemstack = null;
        if(j == 0 || j == 1)
        {
            InventoryPlayer inventoryplayer = entityplayer.inventory;
            if(i == -999)
            {
                if(inventoryplayer.func_20075_i() != null && i == -999)
                {
                    if(j == 0)
                    {
                        entityplayer.dropPlayerItem(inventoryplayer.func_20075_i());
                        inventoryplayer.func_20076_b(null);
                    }
                    if(j == 1)
                    {
                        entityplayer.dropPlayerItem(inventoryplayer.func_20075_i().splitStack(1));
                        if(inventoryplayer.func_20075_i().stackSize == 0)
                        {
                            inventoryplayer.func_20076_b(null);
                        }
                    }
                }
            } else
            {
                Slot slot = (Slot)field_20122_e.get(i);
                if(slot != null)
                {
                    slot.onSlotChanged();
                    ItemStack itemstack1 = slot.getStack();
                    if(itemstack1 != null)
                    {
                        itemstack = itemstack1.copy();
                    }
                    if(itemstack1 != null || inventoryplayer.func_20075_i() != null)
                    {
                        if(itemstack1 != null && inventoryplayer.func_20075_i() == null)
                        {
                            int k = j != 0 ? (itemstack1.stackSize + 1) / 2 : itemstack1.stackSize;
                            inventoryplayer.func_20076_b(slot.func_20004_a(k));
                            if(itemstack1.stackSize == 0)
                            {
                                slot.putStack(null);
                            }
                            slot.onPickupFromSlot();
                        } else
                        if(itemstack1 == null && inventoryplayer.func_20075_i() != null && slot.isItemValid(inventoryplayer.func_20075_i()))
                        {
                            int l = j != 0 ? 1 : inventoryplayer.func_20075_i().stackSize;
                            if(l > slot.getSlotStackLimit())
                            {
                                l = slot.getSlotStackLimit();
                            }
                            slot.putStack(inventoryplayer.func_20075_i().splitStack(l));
                            if(inventoryplayer.func_20075_i().stackSize == 0)
                            {
                                inventoryplayer.func_20076_b(null);
                            }
                        } else
                        if(itemstack1 != null && inventoryplayer.func_20075_i() != null)
                        {
                            if(slot.isItemValid(inventoryplayer.func_20075_i()))
                            {
                                if(itemstack1.itemID != inventoryplayer.func_20075_i().itemID)
                                {
                                    if(inventoryplayer.func_20075_i().stackSize <= slot.getSlotStackLimit())
                                    {
                                        ItemStack itemstack2 = itemstack1;
                                        slot.putStack(inventoryplayer.func_20075_i());
                                        inventoryplayer.func_20076_b(itemstack2);
                                    }
                                } else
                                if(itemstack1.itemID == inventoryplayer.func_20075_i().itemID)
                                {
                                    if(j == 0)
                                    {
                                        int i1 = inventoryplayer.func_20075_i().stackSize;
                                        if(i1 > slot.getSlotStackLimit() - itemstack1.stackSize)
                                        {
                                            i1 = slot.getSlotStackLimit() - itemstack1.stackSize;
                                        }
                                        if(i1 > inventoryplayer.func_20075_i().getMaxStackSize() - itemstack1.stackSize)
                                        {
                                            i1 = inventoryplayer.func_20075_i().getMaxStackSize() - itemstack1.stackSize;
                                        }
                                        inventoryplayer.func_20075_i().splitStack(i1);
                                        if(inventoryplayer.func_20075_i().stackSize == 0)
                                        {
                                            inventoryplayer.func_20076_b(null);
                                        }
                                        itemstack1.stackSize += i1;
                                    } else
                                    if(j == 1)
                                    {
                                        int j1 = 1;
                                        if(j1 > slot.getSlotStackLimit() - itemstack1.stackSize)
                                        {
                                            j1 = slot.getSlotStackLimit() - itemstack1.stackSize;
                                        }
                                        if(j1 > inventoryplayer.func_20075_i().getMaxStackSize() - itemstack1.stackSize)
                                        {
                                            j1 = inventoryplayer.func_20075_i().getMaxStackSize() - itemstack1.stackSize;
                                        }
                                        inventoryplayer.func_20075_i().splitStack(j1);
                                        if(inventoryplayer.func_20075_i().stackSize == 0)
                                        {
                                            inventoryplayer.func_20076_b(null);
                                        }
                                        itemstack1.stackSize += j1;
                                    }
                                }
                            } else
                            if(itemstack1.itemID == inventoryplayer.func_20075_i().itemID && inventoryplayer.func_20075_i().getMaxStackSize() > 1)
                            {
                                int k1 = itemstack1.stackSize;
                                if(k1 > 0 && k1 + inventoryplayer.func_20075_i().stackSize <= inventoryplayer.func_20075_i().getMaxStackSize())
                                {
                                    inventoryplayer.func_20075_i().stackSize += k1;
                                    itemstack1.splitStack(k1);
                                    if(itemstack1.stackSize == 0)
                                    {
                                        slot.putStack(null);
                                    }
                                    slot.onPickupFromSlot();
                                }
                            }
                        }
                    }
                }
            }
        }
        return itemstack;
    }

    public void onCraftGuiClosed(EntityPlayer entityplayer)
    {
        InventoryPlayer inventoryplayer = entityplayer.inventory;
        if(inventoryplayer.func_20075_i() != null)
        {
            entityplayer.dropPlayerItem(inventoryplayer.func_20075_i());
            inventoryplayer.func_20076_b(null);
        }
    }

    public void onCraftMatrixChanged(IInventory iinventory)
    {
        func_20114_a();
    }

    public void func_20119_a(int i, ItemStack itemstack)
    {
        func_20118_a(i).putStack(itemstack);
    }

    public void func_20115_a(ItemStack aitemstack[])
    {
        for(int i = 0; i < aitemstack.length; i++)
        {
            func_20118_a(i).putStack(aitemstack[i]);
        }

    }

    public void func_20112_a(int i, int j)
    {
    }

    public short func_20111_a(InventoryPlayer inventoryplayer)
    {
        craftMatrix++;
        return craftMatrix;
    }

    public void func_20113_a(short word0)
    {
    }

    public void func_20110_b(short word0)
    {
    }

    public abstract boolean func_20120_b(EntityPlayer entityplayer);

    public List field_20123_d;
    public List field_20122_e;
    public int unusedList;
    private short craftMatrix;
    protected List field_20121_g;
    private Set craftResult;
}
