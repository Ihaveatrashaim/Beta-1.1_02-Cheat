package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;

public class Packet15Place extends Packet
{

    public Packet15Place()
    {
    }

    public Packet15Place(int i, int j, int k, int l, ItemStack itemstack)
    {
        id = i;
        xPosition = j;
        yPosition = k;
        zPosition = l;
        direction = itemstack;
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        id = datainputstream.readInt();
        xPosition = datainputstream.read();
        yPosition = datainputstream.readInt();
        zPosition = datainputstream.read();
        short word0 = datainputstream.readShort();
        if(word0 >= 0)
        {
            byte byte0 = datainputstream.readByte();
            byte byte1 = datainputstream.readByte();
            direction = new ItemStack(word0, byte0, byte1);
        } else
        {
            direction = null;
        }
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        dataoutputstream.writeInt(id);
        dataoutputstream.write(xPosition);
        dataoutputstream.writeInt(yPosition);
        dataoutputstream.write(zPosition);
        if(direction == null)
        {
            dataoutputstream.writeShort(-1);
        } else
        {
            dataoutputstream.writeShort(direction.itemID);
            dataoutputstream.writeByte(direction.stackSize);
            dataoutputstream.writeByte(direction.itemDamage);
        }
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handlePlace(this);
    }

    public int getPacketSize()
    {
        return 14;
    }

    public int id;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public ItemStack direction;
}
