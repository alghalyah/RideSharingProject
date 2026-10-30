/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

public class DateTime implements IDateTime {

    private final int year, month, day, hour, minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        java.time.LocalDateTime.of(year, month, day, hour, minute);
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String format() {
        return String.format("%02d/%02d/%04d %02d:%02d", month, day, year, hour, minute);
    }

    public int compareTo(IDateTime other) {
        if (other == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }//compare years first
        int result = Integer.compare(year, other.getYear());
        if (result != 0) {
            return result;
        }//if years are equal then compare the months
        result = Integer.compare(month, other.getMonth());
        if (result != 0) {
            return result;
        }//if months are equal compare the days
        result = Integer.compare(day, other.getDay());
        if (result != 0) {
            return result;
        }//if days are equal compare the hours
        result = Integer.compare(hour, other.getHour());
        if (result != 0) {
            return result;
        }//if hours are equal compare the minutes
        return Integer.compare(minute, other.getMinute());
    }

    public String toString() {
        return format();
    }
}
