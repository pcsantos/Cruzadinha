package com.pirituba.crossword;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Math extends AppCompatActivity {

    private static final int NUMBER_OF_CELLS = 110;
    private List<Content> contentList;
    private List<EditText> editTextList = new ArrayList<>();
    private int hintsNumber = 1;
    private TextView hintsHorizontal;
    private TextView hintsVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        hintsHorizontal = (TextView) findViewById(R.id.txtViewHintHorizontal);
        hintsVertical = (TextView) findViewById(R.id.txtViewHintVertical);

        contentList = new Data("arquivo.txt").loadData();
        loadDisplay();
        loadCrossword();
    }

    private void loadCrossword() {

        for (Content content : contentList) {
            if (content.getHorizontal()) {
                loadHorizontal(content);
            } else {
                loadVertical(content);
            }
        }
    }

    private void loadVertical(Content content) {

        displayHints(content, false);

        int j = content.getFirstCell();
        setAttributesIndexBox(j);
        j += 10;
        hintsNumber++;

        for (int i = 0; i < content.getSize(); i++) {
            EditText editText = editTextList.get(j);
            j += 10;
            editText.setVisibility(View.VISIBLE);
        }
    }

    private void setAttributesIndexBox(int j) {
        EditText index = editTextList.get(j);
        index.setVisibility(View.VISIBLE);
        index.setBackgroundColor(Color.BLACK);
        index.setText("");
        index.setHintTextColor(Color.WHITE);
        index.setHint(String.valueOf(hintsNumber));
        index.setKeyListener(null);
    }

    private void displayHints(Content content, Boolean horizontal) {

        if (horizontal) {
            if (hintsHorizontal.getText().toString().length() > 0) {
                String hint = hintsHorizontal.getText() + "\n" + hintsNumber + " - " + content.getHint();
                hintsHorizontal.setText(hint);
            } else {
                String hint = hintsNumber + " - " + content.getHint();
                hintsHorizontal.setText(hint);
            }
        } else {
            if (hintsVertical.getText().toString().length() > 0) {
                String hint = hintsVertical.getText() + "\n" + hintsNumber + " - " + content.getHint();
                hintsVertical.setText(hint);
            } else {
                String hint = hintsNumber + " - " + content.getHint();
                hintsVertical.setText(hint);
            }
        }
    }

    private void loadHorizontal(Content content) {

        displayHints(content, true);

        int j = content.getFirstCell();
        setAttributesIndexBox(j);
        hintsNumber++;
        j++;

        for (int i = 0; i < content.getSize(); i++) {
            EditText editText = editTextList.get(j++);
            editText.setVisibility(View.VISIBLE);
        }
    }

    private void loadDisplay() {
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            int index = i;
            String editTextID = "editText" + index;
            int resID = getResources().getIdentifier(editTextID, "id", "com.pirituba.crossword");
            EditText editText = (EditText) findViewById(resID);
            editTextList.add(editText);
        }
    }

    public void validateAnswers(View view) {

        List<Content> wrongHints = new ArrayList<>();

        for (Content content : contentList) {
            if (isValid(content)) {
                if (content.getHorizontal()) {
                    setToRightOrWrong(content, true, true);
                } else {
                    setToRightOrWrong(content, false, true);
                }
                displayMessage(content.getWord());
            } else {
                wrongHints.add(content);
            }
        }

        for (Content content : wrongHints) {
            String playerHint = getPlayerHint(content.getFirstCell(),
                    content.getSize(), content.getHorizontal());
            if (!playerHint.equals("")) {
                if (content.getHorizontal()) {
                    setToRightOrWrong(content, true, false);
                } else {
                    setToRightOrWrong(content, false, false);
                }
                displayMessage(content.getCode() + " - " + playerHint +": Incorreta!");
            }
        }
    }

    private void displayMessage(String msg) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void setToRightOrWrong(Content content, Boolean horizontal, Boolean right) {

        if (horizontal) {
            int j = content.getFirstCell() + 1;
            for (int i = 0; i < content.getSize(); i++) {
                EditText editText = editTextList.get(j++);
                if (right) {
                    setBlue(editText);
                } else {
                    setRed(editText);
                }
            }
        } else {
            int j = content.getFirstCell() + 10;
            for (int i = 0; i < content.getSize(); i++) {
                EditText editText = editTextList.get(j);
                if (right) {
                    setBlue(editText);
                } else {
                    setRed(editText);
                }
                j += 10;
            }
        }
    }

    private void setRed(final EditText editText) {

        final int currentColor = editText.getCurrentTextColor();
        if (currentColor == Color.BLACK) {
            editText.setTextColor(Color.RED);
            new CountDownTimer(5000, 50) {
                @Override
                public void onTick(long arg0) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onFinish() {
                    editText.setTextColor(currentColor);
                }
            }.start();
        }

    }

    private void setBlue(EditText editText) {
        editText.setTextColor(Color.BLUE);
        editText.setKeyListener(null);
    }

    private boolean isValid(Content content) {

        if (content.getHorizontal()) {
            String playerHint = getPlayerHint(content.getFirstCell(), content.getSize(), true);
            if (content.getWord().equals(playerHint)) {
                return true;
            }
        } else {
            String playerHint = getPlayerHint(content.getFirstCell(), content.getSize(), false);
            if (content.getWord().equals(playerHint)) {
                return true;
            }
        }
        return false;
    }

    private String getPlayerHint(int firstCell, int size, Boolean horizontal) {

        String hint = "";

        if (horizontal) {
            int j = firstCell + 1;
            for (int i = 0; i < size; i++) {
                EditText editText = editTextList.get(j++);
                hint += editText.getText().toString();
            }
        } else {
            int j = firstCell + 10;
            for (int i = 0; i < size; i++) {
                EditText editText = editTextList.get(j);
                hint += editText.getText().toString();
                j += 10;
            }
        }
        return hint;
    }
}
