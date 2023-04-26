package com.example.watchvideo.Controller;

import org.springframework.ui.Model;

public class Toolkit {
    static String HttpTranslate(String s){
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(i,i+1);
            switch (tmp){
                case "#": tmp = "%23";break;
                case "\"": tmp = "%22";break;
                case "%": tmp = "%25";break;
                case "&": tmp = "%26";break;
                case "(": tmp = "%28";break;
                case ")": tmp = "%29";break;
                case "+": tmp = "%2B";break;
                case ",": tmp = "%2C";break;
                case "/": tmp = "%2F";break;
                case ":": tmp = "%3A";break;
                case ";": tmp = "%3B";break;
                case "<": tmp = "%3C";break;
                case "=": tmp = "%3D";break;
                case ">": tmp = "%3E";break;
                case "?": tmp = "%3F";break;
                case "@": tmp = "%40";break;
                case "\\": tmp = "%5C";break;
                case "|": tmp = "%7C";break;
            }
            res += tmp;
        }
//        while (tmp.contains("#")){
//            tmp = tmp.replace("#", "%23");
//        }
//        while (tmp.contains(" ")){
//            tmp = tmp.replace(" ", "%20");
//        }
//        while (tmp.contains("\"")){
//            tmp = tmp.replace("\"", "%22");
//        }
//        while (tmp.contains("%")){
//            tmp = tmp.replace("%", "%25");
//        }
//        while (tmp.contains("&")){
//            tmp = tmp.replace("&", "%26");
//        }
//        while (tmp.contains("(")){
//            tmp = tmp.replace("(", "%28");
//        }
//        while (tmp.contains(")")){
//            tmp = tmp.replace(")", "%29");
//        }
//        while (tmp.contains("+")){
//            tmp = tmp.replace("+", "%2B");
//        }
//        while (tmp.contains(",")){
//            tmp = tmp.replace(",", "%2C");
//        }
//        while (tmp.contains("/")){
//            tmp = tmp.replace("/", "%2F");
//        }
//        while (tmp.contains(":")){
//            tmp = tmp.replace(":", "%3A");
//        }
//        while (tmp.contains(";")){
//            tmp = tmp.replace(";", "%3B");
//        }
//        while (tmp.contains("<")){
//            tmp = tmp.replace("<", "%3C");
//        }
//        while (tmp.contains("=")){
//            tmp = tmp.replace("=", "%3D");
//        }
//        while (tmp.contains(">")){
//            tmp = tmp.replace(">", "%3E");
//        }
//        while (tmp.contains("?")){
//            tmp = tmp.replace("?", "%3F");
//        }
//        while (tmp.contains("@")){
//            tmp = tmp.replace("@", "%40");
//        }
//        while (tmp.contains("\\")){
//            tmp = tmp.replace("\\", "%5C");
//        }
//        while (tmp.contains("|")){
//            tmp = tmp.replace("|", "%7C");
//        }

        return res;
    }

    public static void errorMsg(int error_id, Model model){
//        cleanErr(session);
//
//        session.setAttribute(errorName[error_id], true);
//        session.setAttribute(errorMsgName[error_id], msg);

//        model.addAttribute("loginErr", new Error(error_id, msg));
        model.addAttribute("error_id", error_id);
    }
}
