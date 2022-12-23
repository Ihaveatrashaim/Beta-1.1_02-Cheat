package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.PrintStream;

public class EntityOtherPlayerMP extends EntityPlayer
{

    public EntityOtherPlayerMP(World world, String s)
    {
        super(world);
        damageRemainder = 0.0F;
        field_771_i = s;
        yOffset = 0.0F;
        stepHeight = 0.0F;
        if(s != null && s.length() > 0)
        {
            field_20047_bv = (new StringBuilder()).append("http://www.minecraft.net/skin/").append(s).append(".png").toString();
            System.out.println((new StringBuilder()).append("Loading texture ").append(field_20047_bv).toString());
        }
        noClip = true;
        renderDistanceWeight = 10D;
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
        return true;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f, 
            float f1, int i)
    {
        yOffset = 0.0F;
        field_784_bh = d;
        field_783_bi = d1;
        field_782_bj = d2;
        field_780_bk = f;
        field_786_bl = f1;
        field_785_bg = i;
    }

    public void onUpdate()
    {
        super.onUpdate();
        field_705_Q = field_704_R;
        double d = posX - prevPosX;
        double d1 = posZ - prevPosZ;
        float f = MathHelper.sqrt_double(d * d + d1 * d1) * 4F;
        if(f > 1.0F)
        {
            f = 1.0F;
        }
        field_704_R += (f - field_704_R) * 0.4F;
        field_703_S += field_704_R;
    }

    public float func_392_h_()
    {
        return 0.0F;
    }

    public void onLivingUpdate()
    {
        super.updatePlayerActionState();
        if(field_785_bg > 0)
        {
            double d = posX + (field_784_bh - posX) / (double)field_785_bg;
            double d1 = posY + (field_783_bi - posY) / (double)field_785_bg;
            double d2 = posZ + (field_782_bj - posZ) / (double)field_785_bg;
            double d3;
            for(d3 = field_780_bk - (double)rotationYaw; d3 < -180D; d3 += 360D) { }
            for(; d3 >= 180D; d3 -= 360D) { }
            rotationYaw += d3 / (double)field_785_bg;
            rotationPitch += (field_786_bl - (double)rotationPitch) / (double)field_785_bg;
            field_785_bg--;
            setPosition(d, d1, d2);
            setRotation(rotationYaw, rotationPitch);
        }
        field_775_e = field_774_f;
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        float f1 = (float)Math.atan(-motionY * 0.20000000298023224D) * 15F;
        if(f > 0.1F)
        {
            f = 0.1F;
        }
        if(!onGround || health <= 0)
        {
            f = 0.0F;
        }
        if(onGround || health <= 0)
        {
            f1 = 0.0F;
        }
        field_774_f += (f - field_774_f) * 0.4F;
        field_9328_R += (f1 - field_9328_R) * 0.8F;
    }

    public boolean isSneaking()
    {
        return field_12240_bw;
    }

    public void func_20045_c(int i, int j)
    {
        ItemStack itemstack = null;
        if(j >= 0)
        {
            itemstack = new ItemStack(j);
        }
        if(i == 0)
        {
            inventory.mainInventory[inventory.currentItem] = itemstack;
        } else
        {
            inventory.armorInventory[i - 1] = itemstack;
        }
    }

    private int field_785_bg;
    private double field_784_bh;
    private double field_783_bi;
    private double field_782_bj;
    private double field_780_bk;
    private double field_786_bl;
    float damageRemainder;
}
