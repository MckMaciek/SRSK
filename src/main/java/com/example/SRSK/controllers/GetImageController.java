package com.example.SRSK.controllers;

import com.example.SRSK.model.ImageDB;
import com.example.SRSK.repositories.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;


@Controller
public class GetImageController {

    @Autowired
    ImageRepo imageRepo;


    //------------------------------------DISPLAYING IMAGE TO THE USER-------------------------------------------------------
    @RequestMapping(value = "/getImage", method = RequestMethod.POST,  produces = MediaType.IMAGE_PNG_VALUE)
    public ModelAndView getImage(@ModelAttribute ImageDB image, Long imageId, ModelMap model, HttpServletResponse response) throws IOException, SQLException {


                try {
                    ImageDB found = imageRepo.findByCode(image.getCode());

                    Blob aBlob = found.getImage();
                    InputStream is = aBlob.getBinaryStream(1, aBlob.length());
                    byte[] imageContent =  aBlob.getBinaryStream(1, aBlob.length()).readAllBytes();

                    model.addAttribute("image", getImgData(aBlob.getBytes(1,(int)aBlob.length())));
                    //System.out.println("BLOB->" + model.getAttribute("image") );

                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                    return new ModelAndView("getCode.html");

                }catch(Exception e){
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return new ModelAndView("getCode.html");
                }



    }
    //------------------------------------/DISPLAYING IMAGE TO THE USER-------------------------------------------------------

    //------------------------------------ENCODER_TO_BASE64-------------------------------------------------------------------
    public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
    //------------------------------------/ENCODER_TO_BASE64-------------------------------------------------------------------

}
