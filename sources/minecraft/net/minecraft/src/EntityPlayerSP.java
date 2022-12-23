package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.PrintStream;
import java.util.Random;

import net.minecraft.cheat.Client;
import net.minecraft.cheat.impl.exploit.GodMod;
import net.minecraft.client.Minecraft;

public class EntityPlayerSP extends EntityPlayer
{

    public EntityPlayerSP(Minecraft minecraft, World world, Session session, int i)
    {
        super(world);
        field_9373_b = 20;
        inPortal = false;
        mc = minecraft;
        dimension = i;
        if(session != null && session.playerName != null && session.playerName.length() > 0)
        {
            field_20047_bv = (new StringBuilder()).append("http://www.minecraft.net/skin/").append(session.playerName).append(".png").toString();
            System.out.println((new StringBuilder()).append("Loading texture ").append(field_20047_bv).toString());
        }
        field_771_i = session.playerName;
    }

    public void updatePlayerActionState()
    {
        super.updatePlayerActionState();
        moveStrafing = movementInput.moveStrafe;
        moveForward = movementInput.moveForward;
        isJumping = movementInput.jump;
    }

    public void onLivingUpdate()
    {
        prevTimeInPortal = timeInPortal;
        if(inPortal)
        {
            if(timeInPortal == 0.0F)
            {
                mc.sndManager.func_337_a("portal.trigger", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
            }
            timeInPortal += 0.0125F;
            if(timeInPortal >= 1.0F)
            {
                timeInPortal = 1.0F;
                field_9373_b = 10;
                mc.sndManager.func_337_a("portal.travel", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
                mc.usePortal();
            }
            inPortal = false;
        } else
        {
            if(timeInPortal > 0.0F)
            {
                timeInPortal -= 0.05F;
            }
            if(timeInPortal < 0.0F)
            {
                timeInPortal = 0.0F;
            }
        }
        if(field_9373_b > 0)
        {
            field_9373_b--;
        }
        movementInput.updatePlayerMoveState(this);
        if(movementInput.sneak && field_9287_aY < 0.2F)
        {
            field_9287_aY = 0.2F;
        }
        super.onLivingUpdate();
    }

    public void resetPlayerKeyState()
    {
        movementInput.resetKeyState();
    }

    public void handleKeyPress(int i, boolean flag)
    {
        movementInput.checkKeyForMovementInput(i, flag);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Score", score);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        score = nbttagcompound.getInteger("Score");
    }

    public void func_20059_m()
    {
        super.func_20059_m();
        mc.displayGuiScreen(null);
    }

    public void displayGUIEditSign(TileEntitySign tileentitysign)
    {
        mc.displayGuiScreen(new GuiEditSign(tileentitysign));
    }

    public void displayGUIChest(IInventory iinventory)
    {
        mc.displayGuiScreen(new GuiChest(inventory, iinventory));
    }

    public void displayWorkbenchGUI(int i, int j, int k)
    {
        mc.displayGuiScreen(new GuiCrafting(inventory, worldObj, i, j, k));
    }

    public void displayGUIFurnace(TileEntityFurnace tileentityfurnace)
    {
        mc.displayGuiScreen(new GuiFurnace(inventory, tileentityfurnace));
    }

    public void onItemPickup(Entity entity, int i)
    {
        mc.effectRenderer.func_1192_a(new EntityPickupFX(mc.theWorld, entity, this, -0.5F));
    }

    public int getPlayerArmorValue()
    {
        return inventory.getTotalArmorValue();
    }

    public void useCurrentItemOnEntity(Entity entity)
    {
        if(entity.interact(this))
        {
            return;
        }
        ItemStack itemstack = getCurrentEquippedItem();
        if(itemstack != null && (entity instanceof EntityLiving))
        {
            itemstack.useItemOnEntity((EntityLiving)entity);
            if(itemstack.stackSize <= 0)
            {
                itemstack.func_1097_a(this);
                destroyCurrentEquippedItem();
            }
        }
    }

    public void sendChatMessage(String s)
    {
    }

    public void func_6420_o()
    {
    }

    public boolean isSneaking()
    {
        return movementInput.sneak;
    }

    public void setInPortal()
    {
        if(field_9373_b > 0)
        {
            field_9373_b = 10;
            return;
        } else
        {
            inPortal = true;
            return;
        }
    }

    public void setHealth(int i)
    {
        int j = health - i;
        if(j <= 0)
        {
            health = i;
        } else
        {
        	if(this == Minecraft.theMinecraft.thePlayer && Client.i.cheatList.getCheat(GodMod.class).isToggled()) return;
        	        	
            field_9346_af = j;
            prevHealth = health;
            field_9306_bj = field_9366_o;
            damageEntity(j);
            hurtTime = maxHurtTime = 10;
        }
    }

    public void respawnPlayer()
    {
        mc.respawn();
    }

    public MovementInput movementInput;
    protected Minecraft mc;
    public int field_9373_b;
    private boolean inPortal;
    public float timeInPortal;
    public float prevTimeInPortal;
}
