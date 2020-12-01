package com.example.SRSK.controllers;

import com.example.SRSK.model.ImageDB;
import com.example.SRSK.repositories.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.AttributedCharacterIterator;

import static org.junit.Assert.fail;

@Controller
public class GetImageController {

    @Autowired
    ImageRepo imageRepo;

    @RequestMapping(value = "/getImage", method = RequestMethod.POST)
    public ModelAndView getImage(@ModelAttribute ImageDB image,  ModelMap model){


        System.out.println(image.getCode());

        ImageDB found = imageRepo.findByCode(image.getCode());
        if(found.getImage() == null){
            System.out.println("Jest nullem");
        }

        System.out.println(found.getImage());

        try {
            Blob aBlob = found.getImage();
            InputStream is = aBlob.getBinaryStream(1, aBlob.length());
            BufferedImage imag= ImageIO.read(is);
            Image image2 = imag;
            ImageIcon icon = new ImageIcon(image2);

            Image img = icon.getImage();
            BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2 = bi.createGraphics();
            g2.drawImage(img, 0, 0, null);
            g2.dispose();
            ImageIO.write(bi, "jpg", new File("pliczek.jpg"));

        }catch (Exception e){
            e.printStackTrace();
        }


        ModelAndView mav = new ModelAndView("getCode.html");
        return mav;
    }

}
