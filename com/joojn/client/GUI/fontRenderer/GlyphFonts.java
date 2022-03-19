package com.joojn.client.GUI.fontRenderer;

import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GlyphFonts {




    public char[] chars = new char[454];

    public GlyphPageFontRenderer ARIAL;
    public GlyphPageFontRenderer ARIAL_TEXTBOX;
    public GlyphPageFontRenderer RUBIK;
    public GlyphPageFontRenderer POPPINS;

    public GlyphFonts(){
        for(int i = 0;i<chars.length;i++)
        {
            chars[i] = (char) i;
        }

        ARIAL = getFont("Arial", 0, 40);
        ARIAL_TEXTBOX = getFont("Arial", 0, 12);
        RUBIK = getFont("rubik", 0, 12);
        //FALLOUT = getFont(new File("C:\\Users\\Asus\\Desktop\\test\\src\\minecraft\\assets\\minecraft\\clientname\\fonts\\r_fallouty.ttf"), 0, 20);
    }

    public GlyphPageFontRenderer getFont(String fontName, int fontType, int size) {
        Font f = new Font(fontName, fontType, size);
        GlyphPage glyphPage = new GlyphPage(f, true, true);
        glyphPage.generateGlyphPage(chars);
        glyphPage.setupTexture();
        return new GlyphPageFontRenderer(glyphPage, glyphPage, glyphPage, glyphPage);
    }

    public GlyphPageFontRenderer getFont(File f, int fontType, int size){
        try {
            Font customFont = Font.createFont(fontType, f).deriveFont(size);
            GlyphPage glyphPage = new GlyphPage(customFont, true, true);
            glyphPage.generateGlyphPage(chars);
            glyphPage.setupTexture();
            return new GlyphPageFontRenderer(glyphPage, glyphPage, glyphPage, glyphPage);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}