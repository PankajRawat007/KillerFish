package com.pankajrawat.killerfish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    TextView scoretext;
    String score;
    Button paly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        scoretext=findViewById(R.id.score);
        paly=findViewById(R.id.play);


        score=getIntent().getExtras().get("score").toString();

        scoretext.setText("Your score is : "+score);

        paly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(GameOver.this,SplashActivity.class);
                startActivity(inte);
            }
        });
    }
}
