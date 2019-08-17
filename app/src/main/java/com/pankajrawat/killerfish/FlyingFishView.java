package com.pankajrawat.killerfish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFishView extends View {

   private Bitmap fish[]=new Bitmap[2];
   private int fishX=10;
   private int fishY;
   private int fishSpeed;
   private int canaswidth,canvasheight;
   private int yellowX,yellowY,yellowSpeed=16;
   private Paint yellowPaint=new Paint();
   private int redX,redY,redSpeed=20;
    private Paint redPaint=new Paint();
    private int greenX,greenY,greenSpeed=20;
    private Paint greenPaint=new Paint();
   private int score,lifecounter;

   private Boolean touch=false;


    private Bitmap backgroundimage;
   private Paint scorepaint=new Paint();

   private Bitmap life[]=new Bitmap[2];


    public FlyingFishView(Context context) {
        super(context);

        fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backgroundimage= BitmapFactory.decodeResource(getResources(),R.drawable.background);

        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);
fishY=550;
score=0;
lifecounter=3;

yellowPaint.setColor(Color.YELLOW);
yellowPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);
        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canaswidth=canvas.getWidth();
        canvasheight=canvas.getHeight();
        canvas.drawBitmap(backgroundimage,0,0,null);
        int minfishY=fish[0].getHeight();
        int maxfishY=canvasheight-fish[0].getHeight()*3;
        fishY=fishY+fishSpeed;
        if(fishY<minfishY){
            fishY=minfishY;
        }
        if(fishY>maxfishY){
            fishY = maxfishY;
        }
        fishSpeed=fishSpeed+2;

        if(touch){
canvas.drawBitmap(fish[1],fishX,fishY,null);
touch=false;
        }
        else {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }


        yellowX=yellowX-yellowSpeed;
        if(hitchecker(yellowX,yellowY)){
            score=score+10;
            yellowX=-100;
        }
        if(yellowX<0)
        {
            yellowX=canaswidth+21;
            yellowY=(int)Math.floor(Math.random()*(maxfishY-minfishY))+minfishY;
        }
        canvas.drawCircle(yellowX,yellowY,25,yellowPaint);


        redX=redX-redSpeed;
        if(hitchecker(redX,redY)){
            
            redX=-100;
            lifecounter--;
            if(lifecounter==0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),GameOver.class);
                intent.putExtra("score",score);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(intent);
            }
            
            
        }
        if(redX<0)
        {
            redX=canaswidth+21;
            redY=(int)Math.floor(Math.random()*(maxfishY-minfishY))+minfishY;
        }
        canvas.drawCircle(redX,redY,35,redPaint);

        greenX=greenX-greenSpeed;
        if(hitchecker(greenX,greenY)){
            score=score+20;
            greenX=-100;
        }
        if(greenX<0)
        {
            greenX=canaswidth+21;
            greenY=(int)Math.floor(Math.random()*(maxfishY-minfishY))+minfishY;
        }
        canvas.drawCircle(greenX,greenY,18,greenPaint);
        canvas.drawText("Score : "+score,20,60,scorepaint);

        for(int i=0;i<3;i++) {
        int x=(int)(580+life[0].getWidth()*1.5*i);
        int y=30;

        if(i<lifecounter){
            canvas.drawBitmap(life[0],x,y,null);

        }
        else
        {
            canvas.drawBitmap(life[1],x,y,null);
        }
        }




    }

    public boolean hitchecker(int x,int y)
    {
        if(fishX<x&&x<(fishX+fish[0].getWidth())&&fishY<y&&y<(fishY+fish[0].getHeight())){
return true;
        }
        return false;

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touch=true;

            fishSpeed=-30;
        }
        return true;
    }
}
