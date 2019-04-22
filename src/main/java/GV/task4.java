package GV;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class task4 {
    void sell(String pathToJsonFile){
        try{
            Gson gson = new Gson();
            Clients clients = gson.fromJson(new FileReader(pathToJsonFile),Clients.class);
            for (int i=0;i<clients.clients.length;i++)
            {

            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
