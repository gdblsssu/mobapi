package com.example.mobapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.expression.spel.ast.TypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    List<Cat> catList = null;
    @GetMapping("/cats")
    public List<Cat> getStr() throws IOException {
        if(catList == null){
        ObjectMapper objectMapper = new ObjectMapper();
            catList = objectMapper.readValue(new File("data.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Cat.class));
        }
        System.out.println("get all list");

        return catList;
    }

    @GetMapping("/cats/{name}")
    public List<Cat> getByName(
            @PathVariable("name") String name
    ) throws IOException {
        if(catList == null){
            ObjectMapper objectMapper = new ObjectMapper();
            catList = objectMapper.readValue(new File("data.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Cat.class));
        }
        List<Cat> temp = new ArrayList<>();
        for(Cat cat: catList){
            if(cat.name.contains(name)){
                temp.add(cat);
            }
        }
        System.out.println("get by: " + name);
        return temp;
    }

}
