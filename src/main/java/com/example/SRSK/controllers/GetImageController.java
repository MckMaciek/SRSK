package com.example.SRSK.controllers;

import com.example.SRSK.model.ImageDB;
import com.example.SRSK.repositories.ImageRepo;
import com.google.common.io.ByteStreams;
import com.google.common.io.InputSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;


@Controller
public class GetImageController {

    @Autowired
    ImageRepo imageRepo;
/*
    @RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@ModelAttribute ImageDB image, @PathVariable("image_id") Long imageId) throws IOException {

        ImageDB found = imageRepo.findByCode(image.getCode());

        System.out.println("COSCOSCOS" + found.getImage());

        //byte [] imageContent =
        //final HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.IMAGE_PNG);
        //return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
        return null;
    }

*/


/*
    @RequestMapping(value = "/displayImage", method = RequestMethod.GET)
    public void displayImage(@ModelAttribute ImageDB image,  ModelMap model, HttpServletResponse response) throws IOException {
        //response.setContentType("image/jpeg");


        BufferedImage in = ImageIO.read(new File("C://Users//Maciej//IdeaProjects//demo//src//main//resources//static/imageKolokw.jpg"));
        System.out.println("NULL CZY NIE " + in);
        byte photo [] = ByteStreams.toByteArray((InputSupplier<? extends InputStream>) in);
        model.addAttribute("image",in);

        //return ByteStreams.toByteArray((InputSupplier<? extends InputStream>) in);
    }
*/


    @RequestMapping(value = "/getImage", method = RequestMethod.POST,  produces = MediaType.IMAGE_PNG_VALUE)
    public ModelAndView getImage(@ModelAttribute ImageDB image, Long imageId, ModelMap model) throws IOException, SQLException {


                ImageDB found = imageRepo.findByCode(image.getCode());

                Blob aBlob = found.getImage();
                InputStream is = aBlob.getBinaryStream(1, aBlob.length());
                byte[] imageContent =  aBlob.getBinaryStream(1, aBlob.length()).readAllBytes();

                model.addAttribute("image", getImgData(aBlob.getBytes(1,(int)aBlob.length())));
                //System.out.println("BLOB->" + model.getAttribute("image") );

                return new ModelAndView("getCode.html");

    }

    public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }


}
