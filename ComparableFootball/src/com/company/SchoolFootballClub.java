package com.company;


import java.util.Objects;

public class SchoolFootballClub extends FootballClub {
    private final String school_name;

    public SchoolFootballClub(String club_name, String location, String school_name) {
        super(club_name, location);
        this.school_name = school_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return Objects.equals(school_name, that.school_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), school_name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


