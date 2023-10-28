package org.example.GameServices;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CheckWord  {
    private List<String> citiesUA;
    private List<String> citiesUsed = new ArrayList<>();
    private String lastComputerWord = "";
    private int score = 0;



    public CheckWord() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File file = new File("src/main/resources/cities.json");
        List<City> cities = objectMapper.readValue(file, new TypeReference<>(){});
        citiesUA = cities.stream()
                .map(a -> a.getName().toLowerCase())
                .collect(Collectors.toList());

    }
    public  int getScore(){
        return score;
    }


    public  void incrementScore(){
        score++;
    }

    public void clear(){
        score = 0;
        citiesUA.addAll(citiesUsed);
        citiesUsed = new ArrayList<>();
        lastComputerWord = "";
    }


    public boolean isGiveUp(String city){
        return city.equals("здаюсь");
    }

    public boolean isValid(String city){
        boolean result = true;
        Pattern p = Pattern.compile("[^а-яА-ЯіІїЇґҐєЄ\\s'-]", Pattern.UNICODE_CHARACTER_CLASS);
        if(city.equals("") || p.matcher(city).find()){
            result = false;
        }
        return result;
    }

    public boolean findAndMatch(String city){
        if (citiesUA.contains(city)){
            citiesUsed.add(city);
            citiesUA.remove(city);
            return true;
        }
        return false;
    }

    public boolean isRepeat(String city){
        return citiesUsed.contains(city);
    }


    public boolean checkFirstLetter(String city){
        if (lastComputerWord.equals("")){
            return true;
        }

        String lastLetter = subLastLetter(lastComputerWord);
        return city.substring(0, 1).equals(lastLetter);
    }

    public String findSuitableWord(String city){

        String result = null;
        for (String c: citiesUA){
            if (c.substring(0, 1).equals(subLastLetter(city))){
                    result = c;
                    result = result.substring(0,1).toUpperCase() + result.substring(1);
                    citiesUsed.add(c);
                    citiesUA.remove(c);
                    lastComputerWord = c;
                    break;
            }
        }
        return result;
    }

    private String subLastLetter(String s){
        s = s.toLowerCase();
        return  (s.endsWith("ь") || s.endsWith("й") || s.endsWith("и"))
                ?  subLastLetter(s.substring(0, s.length()-1))
                : s.substring(s.length() - 1);
    }
}
