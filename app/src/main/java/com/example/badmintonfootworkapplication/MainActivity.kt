package com.example.badmintonfootworkapplication

import android.graphics.Paint
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.badmintonfootworkapplication.ui.theme.BadmintonFootworkApplicationTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController)
        }
        composable("footwork") {
            Footwork(navController)
        }
        composable("video") {
            VideoPage(navController)
        }
    }
}

@Composable
fun Home(navController: NavController) {
    Column (
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text (
            modifier = Modifier
                .padding(top = 25.dp, bottom = 50.dp),
            text = "Badminton Footwork",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button (
                modifier = Modifier
                    .size(width = 150.dp, height = 100.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                     navController.navigate("footwork")
                },

            ){
                Text (
                    text = "Footwork Practise",
                    textAlign = TextAlign.Center
                )
            }

            Button (
                modifier = Modifier
                    .size(width = 150.dp, height = 100.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate("video")
                },
            ){
                Text (
                    text = "Video Tutorial"
                )
            }
        }
    }
}

