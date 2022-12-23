package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import net.minecraft.client.Minecraft;

public class GuiSelectWorld extends GuiScreen
{

    public GuiSelectWorld(GuiScreen guiscreen)
    {
        screenTitle = "Select world";
        selected = false;
        parentScreen = guiscreen;
    }

    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.func_20162_a();
        screenTitle = stringtranslate.func_20163_a("selectWorld.title");
        String s = stringtranslate.func_20163_a("selectWorld.empty");
        String s1 = stringtranslate.func_20163_a("selectWorld.world");
        java.io.File file = Minecraft.getMinecraftDir();
        for(int i = 0; i < 5; i++)
        {
            NBTTagCompound nbttagcompound = World.func_629_a(file, (new StringBuilder()).append("World").append(i + 1).toString());
            if(nbttagcompound == null)
            {
                controlList.add(new GuiButton(i, width / 2 - 100, height / 6 + 24 * i, (new StringBuilder()).append("- ").append(s).append(" -").toString()));
            } else
            {
                String s2 = (new StringBuilder()).append(s1).append(" ").append(i + 1).toString();
                long l = nbttagcompound.getLong("SizeOnDisk");
                s2 = (new StringBuilder()).append(s2).append(" (").append((float)(((l / 1024L) * 100L) / 1024L) / 100F).append(" MB)").toString();
                controlList.add(new GuiButton(i, width / 2 - 100, height / 6 + 24 * i, s2));
            }
        }

        initGui2();
    }

    protected String getWorldName(int i)
    {
        java.io.File file = Minecraft.getMinecraftDir();
        return World.func_629_a(file, (new StringBuilder()).append("World").append(i).toString()) == null ? null : (new StringBuilder()).append("World").append(i).toString();
    }

    public void initGui2()
    {
        StringTranslate stringtranslate = StringTranslate.func_20162_a();
        controlList.add(new GuiButton(5, width / 2 - 100, height / 6 + 120 + 12, stringtranslate.func_20163_a("selectWorld.delete")));
        controlList.add(new GuiButton(6, width / 2 - 100, height / 6 + 168, stringtranslate.func_20163_a("gui.cancel")));
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }
        if(guibutton.id < 5)
        {
            selectWorld(guibutton.id + 1);
        } else
        if(guibutton.id == 5)
        {
            mc.displayGuiScreen(new GuiDeleteWorld(this));
        } else
        if(guibutton.id == 6)
        {
            mc.displayGuiScreen(parentScreen);
        }
    }

    public void selectWorld(int i)
    {
        mc.displayGuiScreen(null);
        if(selected)
        {
            return;
        } else
        {
            selected = true;
            mc.playerController = new PlayerControllerSP(mc);
            mc.func_6247_b((new StringBuilder()).append("World").append(i).toString());
            mc.displayGuiScreen(null);
            return;
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, screenTitle, width / 2, 20, 0xffffff);
        super.drawScreen(i, j, f);
    }

    protected GuiScreen parentScreen;
    protected String screenTitle;
    private boolean selected;
}
