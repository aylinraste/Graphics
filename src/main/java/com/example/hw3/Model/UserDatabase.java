package com.example.hw3.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("ALL")
public class UserDatabase {
    private static ArrayList<User> allUsers = new ArrayList<User>();

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    static public void loadUsers() {
        try {
            File myObj = new File("Users.json");
            if (!myObj.exists())
                myObj.createNewFile();
            List<User> users = new ArrayList<>();
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("Users.json"));
            User[] tempList = gson.fromJson(reader, User[].class);
            if (tempList != null)
                users = Arrays.asList(tempList);
            ArrayList<User> userArrayList = new ArrayList<>(users);
            reader.close();
            allUsers.addAll(userArrayList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public void saveUsers(){
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("Users.json"));
            gson.toJson(allUsers, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(User user){
        allUsers.add(user);
    }

    public static User findUserByUsername(String username){
        for (User user : allUsers) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void sortUsers(){
        Collections.sort(allUsers);
    }
}
