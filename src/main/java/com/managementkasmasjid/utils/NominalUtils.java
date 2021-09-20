package com.managementkasmasjid.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class NominalUtils {
    static String[] counted = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};

    public static String nominalCharacter(Long angka) {
        if (angka < 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nominal should not be lower than 0, Nominal : " + angka);

        if (angka < 12)
            return counted[angka.intValue()];
        else if (angka <= 19)
            return counted[angka.intValue() % 10] + " Belas";
        else if (angka <= 99)
            return nominalCharacter(angka / 10) + " Puluh " + counted[angka.intValue() % 10];
        else if (angka <= 199)
            return "Seratus " + nominalCharacter(angka % 100);
        else if (angka <= 999)
            return nominalCharacter(angka / 100) + " Ratus " + nominalCharacter(angka % 100);
        else if (angka <= 1999)
            return "Seribu " + nominalCharacter(angka % 1000);
        else if (angka <= 999999)
            return nominalCharacter(angka / 1000) + " Ribu " + nominalCharacter(angka % 1000);
        else if (angka <= 999999999)
            return nominalCharacter(angka / 1000000) + " Juta " + nominalCharacter(angka % 1000000);
        else if (angka <= 999999999999L)
            return nominalCharacter(angka / 1000000000) + " Milyar " + nominalCharacter(angka % 1000000000);
        else if (angka <= 999999999999999L)
            return nominalCharacter(angka / 1000000000000L) + " Triliun " + nominalCharacter(angka % 1000000000000L);
        else if (angka <= 999999999999999999L)
            return nominalCharacter(angka / 1000000000000000L) + " Quadrilyun " + nominalCharacter(angka % 1000000000000000L);
        return "";
    }

    public static String nominalToIdrCurrency(Long nominal){
        Locale locale = new Locale("in","ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(locale);
        return formatRupiah.format(nominal);
    }
}
