package com.example.hw3.Model;

public class User implements Comparable<User>{
    private String name;
    private String password;
    private int highScore;
    private double time;
//    private Game game = new Game();


    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getHighScore() {
        return highScore;
    }

    public double getTime() {
        return time;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int compareTo(User o) {
        if (o.highScore - this.highScore != 0)
            return o.highScore - this.highScore;
        return (int) (o.getTime() - this.time);
    }
}
