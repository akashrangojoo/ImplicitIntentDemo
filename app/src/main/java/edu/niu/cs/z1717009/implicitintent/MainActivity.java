package edu.niu.cs.z1717009.implicitintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private Button browserBtn;
    private Button phoneBtn;
    private Button camBtn;
    private ImageView pictureIV;

    static final int REQUEST_CODE =505;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browserBtn = (Button) findViewById(R.id.browserButton);
        phoneBtn = (Button) findViewById(R.id.phoneButton);
        camBtn =  (Button) findViewById(R.id.cameraButton);
        pictureIV = (ImageView) findViewById(R.id.pictureImageView);
    }

    public void openBrowser(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cs.niu.edu"));
        startActivity(browserIntent);
    }

    public void openPhone(View view){
        Intent phoneIntent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9254511314"));
        startActivity(phoneIntent);
    }
    public void openCamera(View view){
        Intent phoneIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(phoneIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(phoneIntent,REQUEST_CODE);
        }
        startActivity(phoneIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode== REQUEST_CODE && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap img = (Bitmap) bundle.get("data");
            pictureIV.setImageBitmap(img);
        }
    }
}
