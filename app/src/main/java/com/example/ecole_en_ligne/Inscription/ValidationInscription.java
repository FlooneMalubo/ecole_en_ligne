package com.example.ecole_en_ligne.Inscription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecole_en_ligne.IndentificationC;
import com.example.ecole_en_ligne.MainActivity;
import com.example.ecole_en_ligne.R;
import com.example.ecole_en_ligne.bdd.EleveManager;

import java.net.Inet4Address;
import java.util.ArrayList;

public class ValidationInscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validation_inscription);

        Intent i = getIntent();

        Button acceuil = (Button) findViewById(R.id.acceuil);
        Button connexion = (Button) findViewById(R.id.connexion);

        TextView idUtilisateur = findViewById(R.id.idUtilisateur);
        TextView textidEnf = findViewById(R.id.TexteIdEnfant);
        TextView idEnf = findViewById(R.id.idEnf);


        EleveManager em = new EleveManager(this);
        ArrayList<String> listId;
        ArrayList<String> listNom;
        ArrayList<String> listPrenom;

        String variable = i.getStringExtra("variable");

        if (variable.contentEquals("parent")){
            idUtilisateur.setText(i.getStringExtra("loginParent"));
            textidEnf.setText(R.string.inscriptionidEnf);
            em.open();
            listId = em.getLoginEleveLoginParent(i.getStringExtra("loginParent"));
            listNom = em.getNomEleveLoginParent(i.getStringExtra("loginParent"));
            listPrenom = em.getPrenomEleveLoginParent(i.getStringExtra("loginParent"));
            em.close();
            String text ="";
            for (int k = 0; k < listId.size();k++){
                text += listNom.get(k) + " " +listPrenom.get(k) + " : "+listId.get(k) + "\n";
            }
            idEnf.setText(text);
        }else if (variable.contentEquals("eleve")){
            idUtilisateur.setText(i.getStringExtra("loginEleve"));
        }


        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connexion = new Intent(ValidationInscription.this, MainActivity.class);
                startActivity(connexion);
                finish();
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connexion = new Intent(ValidationInscription.this, IndentificationC.class);
                startActivity(connexion);
                finish();
            }
        });

    }


}
