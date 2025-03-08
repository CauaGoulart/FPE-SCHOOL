package com.fpe.school.utils;

public class NotaUtils {

    public static String converterParaConceito(double nota) {
        if (nota >= 9.5) return "A+";
        if (nota >= 9.0) return "A";
        if (nota >= 8.5) return "A-";
        if (nota >= 8.0) return "B+";
        if (nota >= 7.5) return "B";
        if (nota >= 7.0) return "B-";
        if (nota >= 6.5) return "C+";
        if (nota >= 6.0) return "C";
        if (nota >= 5.5) return "C-";
        if (nota >= 5.0) return "D+";
        if (nota >= 4.5) return "D";
        if (nota >= 4.0) return "D-";
        return "F"; // Reprovado
    }
}

