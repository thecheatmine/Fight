package fr.iutlens.mmi.fight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        Integer Avie = intent.getIntExtra("A-vie", 0);
        Integer Bvie = intent.getIntExtra("B-vie", 0);
        Integer Rounds = intent.getIntExtra("Rounds", 0);

        if(Avie > Bvie) {
            TextView result = findViewById(R.id.result);
            result.setText("DAIJOUBU");
        }
        else {
            TextView result = findViewById(R.id.result);
            result.setText("NIGERO");
        }

        TextView textAvie = findViewById(R.id.Avie);
        textAvie.setText("A : "+Avie+" vies");
        TextView textBvie = findViewById(R.id.Bvie);
        textBvie.setText("B : "+Bvie+" vies");
        TextView rounds = findViewById(R.id.Rounds);
        rounds.setText(Rounds+" rounds");
    }

    public void onSelect(View jouer) {
        Intent intent = new Intent(this, SelectionActivity.class);
        startActivity(intent);
    }

}
