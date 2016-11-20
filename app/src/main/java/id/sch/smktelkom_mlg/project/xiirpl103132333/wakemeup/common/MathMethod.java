package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.widget.TextView;

import java.util.Random;

/**
 * Created by euiko on 11/20/16.
 */

public class MathMethod {

    public boolean isFinish;
    private boolean res;
    private String question;
    private int resNow, countQuestion, level;
    private TextView restv;

    public MathMethod(TextView resulttv, int lvl) {
        super();
        this.restv = resulttv;
        this.level = lvl;
        countQuestion = 0;
        refreshQuestion(resulttv, lvl);
    }

    public void refreshQuestion(TextView resulttv, int level) {
        getQuestion(level);
        resulttv.setText(question);
    }

    public boolean checkResult(int input) {
        if (resNow == input) {
            res = true;
            refreshQuestion(this.restv, this.level);
        } else {
            res = false;
        }
        return res;
    }

    private void getQuestion(int level) {
        question = "";
        resNow = 0;
        countQuestion += 1;
        String randText = "";
        Random randGen;
        int rando;
        switch (level) {
            case 1:
                randGen = new Random();
                rando = randGen.nextInt(10);
                randText = String.valueOf(rando);
                question = randText + " + ";
                resNow = rando;

                randGen = new Random();
                rando = randGen.nextInt(10);
                randText = String.valueOf(rando);
                resNow += rando;
                question += randText;
                if (countQuestion == 5) isFinish = true;
                break;
            case 2:
                randGen = new Random();
                rando = randGen.nextInt(89);
                rando += 10;
                randText = String.valueOf(rando);
                question = randText + " + ";
                resNow = rando;

                randGen = new Random();
                rando = randGen.nextInt(89);
                rando += 10;
                randText = String.valueOf(rando);
                question += randText;
                resNow += rando;
                if (countQuestion == 5) isFinish = true;
                break;
            case 3:
                randGen = new Random();
                rando = randGen.nextInt(100);
                rando += 50;
                randText = String.valueOf(rando);
                question = randText + " + ";
                resNow = rando;

                randGen = new Random();
                rando = randGen.nextInt(100);
                rando += 50;
                randText = String.valueOf(rando);
                question += randText + " - ";
                resNow += rando;

                randGen = new Random();
                rando = randGen.nextInt(50);
                rando += 10;
                randText = String.valueOf(rando);
                question += randText;
                resNow -= rando;
                if (countQuestion == 4) isFinish = true;
                break;
            case 4:
                randGen = new Random();
                rando = randGen.nextInt(10);
                rando += 5;
                randText = String.valueOf(rando);
                question = "(" + randText + " * ";
                resNow = rando;

                randGen = new Random();
                rando = randGen.nextInt(30);
                rando += 10;
                randText = String.valueOf(rando);
                question += randText + " ) - ";
                resNow *= rando;

                randGen = new Random();
                rando = randGen.nextInt(50);
                rando += 10;

                while (rando > resNow) rando -= 40;
                randText = String.valueOf(rando);
                question += randText;
                resNow -= rando;
                if (countQuestion == 3) isFinish = true;
                break;
            case 5:
                randGen = new Random();
                rando = randGen.nextInt(50);
                rando += 20;
                randText = String.valueOf(rando);
                question = "(" + randText + " * ";
                resNow = rando;

                randGen = new Random();
                rando = randGen.nextInt(100);
                rando += 50;
                randText = String.valueOf(rando);
                question += randText + " ) - ";
                resNow *= rando;

                randGen = new Random();
                rando = randGen.nextInt(150);
                rando += 50;
                while (rando > resNow) rando -= 150;
                randText = String.valueOf(rando);
                question += randText;
                resNow -= rando;
                break;
            default:
                getQuestion(5);
                break;
        }
    }


}
