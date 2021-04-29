package Servlets;

import java.io.PrintWriter;

public class Error {
    //prints any error string on new html page
    public static void printError(PrintWriter out, String msg){
        out.println("<!DOCTYPE html");
        out.println("<html>");
        out.println("<head><title>Error</title></head>");
        out.println("<body style=\"font-family:Times new roman;\"><center><h3>Error</h3>");
        out.println("<p><h3>"+msg+"</h3></p>");
        out.println("</center></body></html>");
    }
}
