package edu.gcu.betterweather;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AboutBwaView {

    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main);
            title = "About Page";
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            window.decorView.systemUiVisibility = (
                    View.generateViewId());
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            val fancyAboutPage = findViewById<FancyAboutPage>(R.id.fancyaboutpage)
                    //fancyAboutPage.setCoverTintColor(Color.BLUE); //Optional
                    fancyAboutPage.setCover(R.drawable.coverimg)
            fancyAboutPage.setName("Nathan Meyer");
            fancyAboutPage.setName("Edward Lale ");
            fancyAboutPage.setName("Lindsay Blood");
            fancyAboutPage.setDescription("2022 - 2034 Weather App Inc.");
            fancyAboutPage.setAppIcon(R.drawable.weather-2019-02-07);
            fancyAboutPage.setAppName("Better Weather App");
            fancyAboutPage.setVersionNameAsAppSubTitle("1.0.0");
            fancyAboutPage.addGitHubLink("");
        }
    }
}
