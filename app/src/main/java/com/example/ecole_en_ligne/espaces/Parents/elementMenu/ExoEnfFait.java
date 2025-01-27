package com.example.ecole_en_ligne.espaces.Parents.elementMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ecole_en_ligne.MainActivity;
import com.example.ecole_en_ligne.R;
import com.example.ecole_en_ligne.bdd.ExerciceManager;
import com.example.ecole_en_ligne.espaces.Parents.EspaceParents;
import com.example.ecole_en_ligne.espaces.Parents.ProfilParent;
import com.example.ecole_en_ligne.espaces.Parents.SuiviEnfantI;
import com.google.android.material.navigation.NavigationView;


public class ExoEnfFait extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView retour;
    private ImageView menu;
    private Intent i;
    private TextView nbExosFaits;
    private double percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enf_exo_fait);

        i = getIntent();

        TextView loginName = findViewById(R.id.loginName);
        String myLogin = i.getStringExtra("Login");
        loginName.setText(" " + myLogin);

        TextView texte = findViewById(R.id.texte_exo_fait);
        nbExosFaits = findViewById(R.id.nombre_exo_fait);
        retour = findViewById(R.id.retour);
        menu = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigationP);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutP);
        navigationView.setNavigationItemSelectedListener(this);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        ExerciceManager exM = new ExerciceManager(this);

        exM.open();
        double nbExoFait = exM.nbExosFait(i.getStringExtra("loginEleve"));
        double nbExoTotal = exM.nbExosTotal(i.getStringExtra("loginEleve"));
        exM.close();

        if(nbExoTotal != 0) {
            percentage = ((nbExoFait / nbExoTotal) * 100);
            System.out.println("POURCENTAGE : "+percentage);
        } else {
            percentage = 0;
        }

        texte.setText(R.string.exoRealises);
        nbExosFaits.setText((int)nbExoFait + " / " + (int)nbExoTotal + "\t\t\t(" + percentage + "%)");


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tableau_bordP: {
                Intent redir = new Intent(getApplicationContext(), EspaceParents.class);
                redir.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                redir.putExtra("Login",i.getStringExtra("Login"));
                startActivity(redir);
                finish();
                break;
            }
            case R.id.cours_exos_fait: {
                break;
            }
            case R.id.momentCO: {
                Intent redir = new Intent(getApplicationContext(), SuiviEnfantI.class);
                redir.putExtra("Login",i.getStringExtra("Login"));
                redir.putExtra("Content","Moment de connexion");
                Intent intent = new Intent(getApplicationContext(), EspaceParents.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                startActivity(redir);
                break;
            }
            case R.id.courbes_progressionP: {
                Intent redir = new Intent(getApplicationContext(), SuiviEnfantI.class);
                redir.putExtra("Login",i.getStringExtra("Login"));
                redir.putExtra("Content","Courbes de progressions");
                Intent intent = new Intent(getApplicationContext(), EspaceParents.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                startActivity(redir);
                break;
            }
            case R.id.recommandationsP: {
                Intent redir = new Intent(getApplicationContext(), SuiviEnfantI.class);
                redir.putExtra("Login",i.getStringExtra("Login"));
                redir.putExtra("Content","Recommandation");
                Intent intent = new Intent(getApplicationContext(), EspaceParents.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                startActivity(redir);
                break;
            }
            case R.id.rappel: {
                Intent redir = new Intent(getApplicationContext(), SuiviEnfantI.class);
                redir.putExtra("Login",i.getStringExtra("Login"));
                redir.putExtra("Content","Définir un rappel");
                Intent intent = new Intent(getApplicationContext(), EspaceParents.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                startActivity(redir);
                break;
            }
            case R.id.profil: {
                Intent redir = new Intent(getApplicationContext(), ProfilParent.class);
                redir.putExtra("Login",i.getStringExtra("Login"));
                Intent intent = new Intent(getApplicationContext(), EspaceParents.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                startActivity(redir);
                finish();
                break;
            }
            case R.id.deconnexion: {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawers();
        return true;
    }
}
