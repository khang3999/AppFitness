package com.example.homefitness.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;


    public class Account {
        //Dinh nghia thuoc tinh cua bang
        private int id ;
        private String name;
        private String gender;
        private double height;
        private double weight;
        private String target;
        private String idRecentExercise;

        public String getIdRecentExercise() {
            return idRecentExercise;
        }

        public void setIdRecentExercise(String idRecentExercise) {
            this.idRecentExercise = idRecentExercise;
        }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }



    public Account(int id, String name, String gender, double height, double weight, String target, String idRecentExercise) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.height = height;
            this.weight = weight;
            this.target = target;
            this.idRecentExercise =idRecentExercise;
        }

        public Account( String name, String gender, double height, double weight, String target, String idRecentExercise) {

            this.name = name;
            this.gender = gender;
            this.height = height;
            this.weight = weight;
            this.target = target;
            this.idRecentExercise = idRecentExercise;
        }
        public Account() {
            this.id = 1;
            this.name = "unknown";
            this.gender = "unknown";
            this.height = 0;
            this.weight = 0;
            this.target = "unknown";
            this.idRecentExercise="unknown";
        }
        @NonNull
        @Override
        public String toString() {
            return super.toString();
        }
    }
