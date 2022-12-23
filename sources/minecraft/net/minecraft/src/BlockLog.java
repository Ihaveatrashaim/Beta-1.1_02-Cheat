package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class BlockLog extends Block
{

    protected BlockLog(int i)
    {
        super(i, Material.wood);
        blockIndexInTexture = 20;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public int idDropped(int i, Random random)
    {
        return Block.wood.blockID;
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        byte byte0 = 4;
        int l = byte0 + 1;
        if(world.checkChunksExist(i - l, j - l, k - l, i + l, j + l, k + l))
        {
            for(int i1 = -byte0; i1 <= byte0; i1++)
            {
                for(int j1 = -byte0; j1 <= byte0; j1++)
                {
                    for(int k1 = -byte0; k1 <= byte0; k1++)
                    {
                        int l1 = world.getBlockId(i + i1, j + j1, k + k1);
                        if(l1 == Block.leaves.blockID && world.getBlockMetadata(i + i1, j + j1, k + k1) != 7)
                        {
                            world.setBlockMetadata(i + i1, j + j1, k + k1, 7);
                        }
                    }

                }

            }

        }
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1)
        {
            return 21;
        }
        return i != 0 ? 20 : 21;
    }
}
