package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;

public class Packet102 extends Packet
{

    public Packet102()
    {
    }

    public Packet102(int i, int j, int k, ItemStack itemstack, short word0)
    {
        field_20024_a = i;
        field_20023_b = j;
        field_20027_c = k;
        field_20025_e = itemstack;
        field_20026_d = word0;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.func_20091_a(this);
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        field_20024_a = datainputstream.readByte();
        field_20023_b = datainputstream.readShort();
        field_20027_c = datainputstream.readByte();
        field_20026_d = datainputstream.readShort();
        short word0 = datainputstream.readShort();
        if(word0 >= 0)
        {
            byte byte0 = datainputstream.readByte();
            byte byte1 = datainputstream.readByte();
            field_20025_e = new ItemStack(word0, byte0, byte1);
        } else
        {
            field_20025_e = null;
        }
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        dataoutputstream.writeByte(field_20024_a);
        dataoutputstream.writeShort(field_20023_b);
        dataoutputstream.writeByte(field_20027_c);
        dataoutputstream.writeShort(field_20026_d);
        if(field_20025_e == null)
        {
            dataoutputstream.writeShort(-1);
        } else
        {
            dataoutputstream.writeShort(field_20025_e.itemID);
            dataoutputstream.writeByte(field_20025_e.stackSize);
            dataoutputstream.writeByte(field_20025_e.itemDamage);
        }
    }

    public int getPacketSize()
    {
        return 10;
    }

    public int field_20024_a;
    public int field_20023_b;
    public int field_20027_c;
    public short field_20026_d;
    public ItemStack field_20025_e;
}
