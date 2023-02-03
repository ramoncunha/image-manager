package com.image.manager.controller;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class ImageController {
    
    @PostMapping("/image")
    public ResponseEntity<String> receiveImage(@RequestBody byte[] imageBytes) throws IOException, TikaException, SAXException {

        File output = new File("out.jpg");
        Tika tika = new Tika();
        TikaInputStream tikaStream = TikaInputStream.get(imageBytes);
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        Parser parser = new AutoDetectParser();
        ParseContext context = new ParseContext();
        parser.parse(tikaStream, handler, metadata, context);
        tikaStream.close();
        FileOutputStream outputStream = new FileOutputStream(output);
        outputStream.write(tikaStream.toString().getBytes());
        outputStream.close();


//        ImageIO.write(bufferedImage, "JPG", new File("newfile.jpg"));


//        Tika tika = new Tika();
//        Parser parser = new AutoDetectParser();
//        Metadata metadata = new Metadata();
//        metadata.set(Metadata.CONTENT_TYPE, "image/heic");
//        ParseContext parseContext = new ParseContext();
//        parseContext.set(Parser.class, parser);
//
//        parser.parse(new ByteArrayInputStream(imageBytes), handler, metadata, context);
//
//        try (FileOutputStream jpgOutputStream = new FileOutputStream(outputFile)) {
//            tika.getParser().parse(new ByteArrayInputStream(imageBytes), new BodyContentHandler(jpgOutputStream), metadata, parseContext);
//        }

        return ResponseEntity.ok("imageBytes");
    }
}
