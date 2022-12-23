package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

public class GuiDownloadTerrain extends GuiScreen
{

    public GuiDownloadTerrain(NetClientHandler netclienthandler)
    {
        updateCounter = 0;
        netHandler = netclienthandler;
    }

    protected void keyTyped(char c, int i)
    {
    }

    public void initGui()
    {
        controlList.clear();
    }

    public void updateScreen()
    {
        updateCounter++;
        if(updateCounter % 20 == 0)
        {
            netHandler.addToSendQueue(new Packet0KeepAlive());
        }
        if(netHandler != null)
        {
            netHandler.processReadPackets();
        }
    }

    protected void actionPerformed(GuiButton guibutton)
    {
    }

    public void drawScreen(int i, int j, float f)
    {
        drawBackground(0);
        StringTranslate stringtranslate = StringTranslate.func_20162_a();
        drawCenteredString(fontRenderer, stringtranslate.func_20163_a("multiplayer.downloadingTerrain"), width / 2, height / 2 - 50, 0xffffff);
        super.drawScreen(i, j, f);
    }

    private NetClientHandler netHandler;
    private int updateCounter;
}
