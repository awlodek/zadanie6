package com.example.przemcio.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.przemcio.myapplication.R.id;
import static com.example.przemcio.myapplication.R.layout;
import static com.example.przemcio.myapplication.R.raw;

public class MainActivity extends AppCompatActivity {

    private Button btnNewAlertDialogButton;
    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        btnNewAlertDialogButton = (Button) findViewById(id.btnNewAlertDialogButton);
        Button lista = (Button) findViewById(id.button);

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLista();
            }
        });


        btnNewAlertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlertDialogWithButtons();
            }
        });

        Button nagrywanie = (Button) findViewById(R.id.button2);

        nagrywanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context;
                context = getApplicationContext();
                Intent intent = new Intent(context,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    public void play(View v)
    {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this,raw.piosenka1);
        mediaPlayer.start();

    }

    public void playSound(int x) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        switch (x)
        {
            case 0:
                mediaPlayer=MediaPlayer.create(this, raw.piosenka1);
                mediaPlayer.start();
                break;
            case 1:
                mediaPlayer=MediaPlayer.create(this, raw.piosenka2);
                mediaPlayer.start();
                break;
        }
       // mediaPlayer = MediaPlayer.create(this,raw.piosenka1);
        //mediaPlayer.start();
    }

    public void stopSound(View view) {

        if (mediaPlayer == null) {
            mediaPlayer.release();
        }
        else mediaPlayer.stop();
    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }

    private void createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wyjście");
        dialogBuilder.setMessage("Czy napewno?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                mediaPlayer.stop();
                showToast("Wychodzę");
                finish();
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Anulowaleś wyjście");

            }
        });
        dialogBuilder.create();
        dialogBuilder.show();
    }

    private Dialog createLista() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {"Pierwsza piosenka", "Druga piosenka"};
        dialogBuilder.setTitle("Lista piosenek");
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                showToast("Wybrałeś: " + options[position]);
                playSound(position);

            }
        });
        dialogBuilder.create();
        dialogBuilder.show();
        return dialogBuilder.create();
    }
};

