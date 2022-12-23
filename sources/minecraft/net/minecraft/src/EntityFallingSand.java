package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class EntityFallingSand extends Entity
{

    public EntityFallingSand(World world)
    {
        super(world);
        fallTime = 0;
    }

    public EntityFallingSand(World world, double d, double d1, double d2, 
            int i)
    {
        super(world);
        fallTime = 0;
        entityID = i;
        preventEntitySpawning = true;
        setSize(0.98F, 0.98F);
        yOffset = height / 2.0F;
        setPosition(d, d1, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        entityWalks = false;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }

    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    public void onUpdate()
    {
        if(entityID == 0)
        {
            setEntityDead();
            return;
        }
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        fallTime++;
        motionY -= 0.039999999105930328D;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.98000001907348633D;
        motionY *= 0.98000001907348633D;
        motionZ *= 0.98000001907348633D;
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(posY);
        int k = MathHelper.floor_double(posZ);
        if(worldObj.getBlockId(i, j, k) == entityID)
        {
            worldObj.setBlockWithNotify(i, j, k, 0);
        }
        if(onGround)
        {
            motionX *= 0.69999998807907104D;
            motionZ *= 0.69999998807907104D;
            motionY *= -0.5D;
            setEntityDead();
            if(!worldObj.canBlockBePlacedAt(entityID, i, j, k, true) || !worldObj.setBlockWithNotify(i, j, k, entityID))
            {
                dropItem(entityID, 1);
            }
        } else
        if(fallTime > 100)
        {
            dropItem(entityID, 1);
            setEntityDead();
        }
    }

    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("Tile", (byte)entityID);
    }

    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        entityID = nbttagcompound.getByte("Tile") & 0xff;
    }

    public float func_392_h_()
    {
        return 0.0F;
    }

    public World func_465_i()
    {
        return worldObj;
    }

    public int entityID;
    public int fallTime;
}
