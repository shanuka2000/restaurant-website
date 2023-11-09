package com.example.signature_cuisine.services.impl.formating;

import com.example.signature_cuisine.services.DateProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateProcess implements DateProcessor {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date processDate(String date) throws Exception {
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            throw new Exception("Date processing failed");
        }
    }
}
