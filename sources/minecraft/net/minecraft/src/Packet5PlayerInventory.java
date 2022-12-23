package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;

public class Packet5PlayerInventory extends Packet
{

    public Packet5PlayerInventory()
    {
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        type = datainputstream.readInt();
        stacks = datainputstream.readShort();
        field_20044_c = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        dataoutputstream.writeInt(type);
        dataoutputstream.writeShort(stacks);
        dataoutputstream.writeShort(field_20044_c);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handlePlayerInventory(this);
    }

    public int getPacketSize()
    {
        return 8;
    }

    public int type;
    public int stacks;
    public int field_20044_c;
}
