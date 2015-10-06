package com.krugdev.gestordeestudo;

import android.graphics.Color;

/**
 * Created by Krug on 17/09/2015.
 */
public class Cor {

    public static int qtd (){
        return 20;
    }

    public static int getCor (int cod) {

        int cor = 0;

        switch (cod) {

            case 0:
                cor = Color.argb(255, 255, 0, 0);
                break;

            case 1:
                cor = Color.argb(255, 0, 255, 0);
                break;

            case 2:
                cor = Color.argb(255, 0, 0, 255);
                break;

            case 3:
                cor = Color.argb(255, 255, 255, 0);
                break;

            case 4:
                cor = Color.argb(255, 255, 0, 255);
                break;

            case 5:
                cor = Color.argb(255, 255, 255, 255);
                break;

            case 6:
                cor = Color.argb(255, 0, 255, 255);
                break;

            case 7:
                cor = Color.argb(255, 150, 0, 0);
                break;

            case 8:
                cor = Color.argb(255, 0, 150, 0);
                break;

            case 9:
                cor = Color.argb(255, 0, 0, 150);
                break;

            case 10:
                cor = Color.argb(255, 150, 0, 0);
                break;

            case 11:
                cor = Color.argb(255, 150, 150, 0);
                break;

            case 12:
                cor = Color.argb(255, 150, 0, 150);
                break;

            case 13:
                cor = Color.argb(255, 150, 150, 150);
                break;

            case 14:
                cor = Color.argb(255, 0, 150, 150);
                break;

            case 15:
                cor = Color.argb(255, 255,150, 0);
                break;

            case 16:
                cor = Color.argb(255, 255, 0, 150);
                break;

            case 17:
                cor = Color.argb(255, 255, 150, 150);
                break;

            case 18:
                cor = Color.argb(255, 150, 255, 0);
                break;

            case 19:
                cor = Color.argb(255, 0, 255, 150);
                break;


            default:
                cor = Color.argb(255, 0, 0, 0);
        }

        return cor;
    }
}
