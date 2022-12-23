package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


class ThreadRunIsoClient extends Thread
{

    ThreadRunIsoClient(CanvasIsomPreview canvasisompreview)
    {
        isoCanvas = canvasisompreview;
    }

    public void run()
    {
        while(CanvasIsomPreview.isRunning(isoCanvas)) 
        {
            isoCanvas.func_1265_d();
            try
            {
                Thread.sleep(1L);
            }
            catch(Exception exception) { }
        }
    }

    final CanvasIsomPreview isoCanvas; /* synthetic field */
}
