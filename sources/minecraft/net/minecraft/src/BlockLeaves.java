package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class BlockLeaves extends BlockLeavesBase
{

    protected BlockLeaves(int i, int j)
    {
        super(i, j, Material.leaves, false);
        baseIndexInPNG = j;
        setTickOnLoad(true);
    }

    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        iblockaccess.func_4075_a().func_4069_a(i, k, 1, 1);
        double d = iblockaccess.func_4075_a().temperature[0];
        double d1 = iblockaccess.func_4075_a().humidity[0];
        return ColorizerFoliage.func_4146_a(d, d1);
    }

    public void func_308_h(World world, int i, int j, int k)
    {
        int l = 1;
        int i1 = l + 1;
        if(world.checkChunksExist(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1))
        {
            for(int j1 = -l; j1 <= l; j1++)
            {
                for(int k1 = -l; k1 <= l; k1++)
                {
                    for(int l1 = -l; l1 <= l; l1++)
                    {
                        int i2 = world.getBlockId(i + j1, j + k1, k + l1);
                        if(i2 == Block.leaves.blockID)
                        {
                            world.setBlockMetadata(i + j1, j + k1, k + l1, 7);
                        }
                    }

                }

            }

        }
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(world.multiplayerWorld)
        {
            return;
        }
        if(world.getBlockMetadata(i, j, k) == 7)
        {
            byte byte0 = 4;
            int l = byte0 + 1;
            byte byte1 = 32;
            int i1 = byte1 * byte1;
            int j1 = byte1 / 2;
            if(field_20017_a == null)
            {
                field_20017_a = new int[byte1 * byte1 * byte1];
            }
            if(world.checkChunksExist(i - l, j - l, k - l, i + l, j + l, k + l))
            {
                for(int k1 = -byte0; k1 <= byte0; k1++)
                {
                    for(int j2 = -byte0; j2 <= byte0; j2++)
                    {
                        for(int l2 = -byte0; l2 <= byte0; l2++)
                        {
                            int j3 = world.getBlockId(i + k1, j + j2, k + l2);
                            if(j3 == Block.wood.blockID)
                            {
                                field_20017_a[(k1 + j1) * i1 + (j2 + j1) * byte1 + (l2 + j1)] = 0;
                                continue;
                            }
                            if(j3 == Block.leaves.blockID)
                            {
                                field_20017_a[(k1 + j1) * i1 + (j2 + j1) * byte1 + (l2 + j1)] = -2;
                            } else
                            {
                                field_20017_a[(k1 + j1) * i1 + (j2 + j1) * byte1 + (l2 + j1)] = -1;
                            }
                        }

                    }

                }

                for(int l1 = 1; l1 <= 4; l1++)
                {
                    for(int k2 = -byte0; k2 <= byte0; k2++)
                    {
                        for(int i3 = -byte0; i3 <= byte0; i3++)
                        {
                            for(int k3 = -byte0; k3 <= byte0; k3++)
                            {
                                if(field_20017_a[(k2 + j1) * i1 + (i3 + j1) * byte1 + (k3 + j1)] != l1 - 1)
                                {
                                    continue;
                                }
                                if(field_20017_a[((k2 + j1) - 1) * i1 + (i3 + j1) * byte1 + (k3 + j1)] == -2)
                                {
                                    field_20017_a[((k2 + j1) - 1) * i1 + (i3 + j1) * byte1 + (k3 + j1)] = l1;
                                }
                                if(field_20017_a[(k2 + j1 + 1) * i1 + (i3 + j1) * byte1 + (k3 + j1)] == -2)
                                {
                                    field_20017_a[(k2 + j1 + 1) * i1 + (i3 + j1) * byte1 + (k3 + j1)] = l1;
                                }
                                if(field_20017_a[(k2 + j1) * i1 + ((i3 + j1) - 1) * byte1 + (k3 + j1)] == -2)
                                {
                                    field_20017_a[(k2 + j1) * i1 + ((i3 + j1) - 1) * byte1 + (k3 + j1)] = l1;
                                }
                                if(field_20017_a[(k2 + j1) * i1 + (i3 + j1 + 1) * byte1 + (k3 + j1)] == -2)
                                {
                                    field_20017_a[(k2 + j1) * i1 + (i3 + j1 + 1) * byte1 + (k3 + j1)] = l1;
                                }
                                if(field_20017_a[(k2 + j1) * i1 + (i3 + j1) * byte1 + ((k3 + j1) - 1)] == -2)
                                {
                                    field_20017_a[(k2 + j1) * i1 + (i3 + j1) * byte1 + ((k3 + j1) - 1)] = l1;
                                }
                                if(field_20017_a[(k2 + j1) * i1 + (i3 + j1) * byte1 + (k3 + j1 + 1)] == -2)
                                {
                                    field_20017_a[(k2 + j1) * i1 + (i3 + j1) * byte1 + (k3 + j1 + 1)] = l1;
                                }
                            }

                        }

                    }

                }

            }
            int i2 = field_20017_a[j1 * i1 + j1 * byte1 + j1];
            if(i2 >= 0)
            {
                world.setBlockMetadataWithNotify(i, j, k, 0);
            } else
            {
                func_6360_i(world, i, j, k);
            }
        }
    }

    private void func_6360_i(World world, int i, int j, int k)
    {
        dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
        world.setBlockWithNotify(i, j, k, 0);
    }

    public int quantityDropped(Random random)
    {
        return random.nextInt(16) != 0 ? 0 : 1;
    }

    public int idDropped(int i, Random random)
    {
        return Block.sapling.blockID;
    }

    public boolean isOpaqueCube()
    {
        return !graphicsLevel;
    }

    public void setGraphicsLevel(boolean flag)
    {
        graphicsLevel = flag;
        blockIndexInTexture = baseIndexInPNG + (flag ? 0 : 1);
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
        super.onEntityWalking(world, i, j, k, entity);
    }

    private int baseIndexInPNG;
    int field_20017_a[];
}
