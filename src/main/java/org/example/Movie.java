package org.example;

import java.util.ArrayList;

public class Movie {
    static int nbMovies;
    private int id;
    private String title;
    private ArrayList<Integer> release_date;
    private ArrayList<String> genre;
    private String MPAA_tag;
    private int duration;
    private ArrayList<String> directors;
    private ArrayList<String> actors;
    private String overview;
    private int user_score;
    private int number_of_vote;
    private int budget;
    private int revenue;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Integer> getRelease_date() {
        return release_date;
    }

    public void setRelease_date(ArrayList<Integer> release_date) {
        this.release_date = release_date;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getMPAA_tag() {
        return MPAA_tag;
    }

    public void setMPAA_tag(String MPAA_tag) {
        this.MPAA_tag = MPAA_tag;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getUser_score() {
        return user_score;
    }

    public void setUser_score(int user_score) {
        this.user_score = user_score;
    }

    public int getNumber_of_vote() {
        return number_of_vote;
    }

    public void setNumber_of_vote(int number_of_vote) {
        this.number_of_vote = number_of_vote;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }


    public Movie(){
        nbMovies++;
        this.id = nbMovies;
    }
    public void printData(){
        System.out.println("-----------------------------------------------");
        System.out.println("ID:              " + this.id);
        System.out.println("Title:           " + this.title);
        System.out.println("Release Date:    " + this.release_date);
        System.out.println("Genre:           " + this.genre);
        System.out.println("MPAA tag:        " + this.MPAA_tag);
        System.out.println("Duration:        " + this.duration + " minutes");
        System.out.println("Directors:       " + this.directors);
        System.out.println("Actors:          " + this.actors);
        System.out.println("Overview:        " + this.overview);
        System.out.println("User Score:      " + this.user_score);
        System.out.println("Number of Votes: " + this.number_of_vote);
        System.out.println("Budget:          $" + this.budget);
        System.out.println("Revenue:         $" + this.revenue);
        System.out.println("-----------------------------------------------");

    }

    public void printTitle(){
        System.out.println("ID:              " + this.id);
        System.out.println("Title:           " + this.title);
    }
}
