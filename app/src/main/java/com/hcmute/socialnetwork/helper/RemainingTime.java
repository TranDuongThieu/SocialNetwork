package com.hcmute.socialnetwork.helper;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RemainingTime {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String remainingTime(LocalDateTime fromDateTime) {

        LocalDateTime toDateTime = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);

        if (years > 0) return years + " năm trước";
        else if (months > 0) return months + " tháng trước";
        else if (days > 0) return days + " ngày trước";
        else if (hours > 0) return hours + " giờ trước";
        else if (minutes > 0) return minutes + " phút trước";
        else return "Vừa xong ";
    }
}
