package com.example.badmintonfootworkapplication

import android.graphics.drawable.shapes.Shape
import android.util.MutableLong
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

@Composable
fun Footwork(navController: NavController) {
    val courts = listOf(
        R.drawable.badminton_court,
        R.drawable.badminton_court_bottom_left,
        R.drawable.badminton_court_bottom_right,
        R.drawable.badminton_court_top_left,
        R.drawable.badminton_court_top_right,
        R.drawable.badminton_court_center_left,
        R.drawable.badminton_court_center_right,
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val courtIndex = remember { mutableIntStateOf(0) }
        CourtCard(courts, courtIndex)
        ScoreCard(courts.size, courtIndex)
    }
}

@Composable
fun CourtCard(courts : List<Int>, courtIndex: MutableIntState) {
    Image(
        modifier = Modifier
            .size(350.dp, 280.dp), // aspect ratio is 5 : 4
        painter = painterResource(id = courts[courtIndex.intValue]),
        contentDescription = "Court PNG"
    )
}

@Composable
fun ScoreCard(size: Int, courtIndex: MutableIntState) {
    val timerDuration = remember { mutableLongStateOf(0) }
    val value = remember { mutableStateOf("") }
    val isRunning = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextField(modifier = Modifier.size(width = 150.dp, height = 50.dp), value = value.value, onValueChange = {
            value.value = it
            if (it.toLongOrNull() != null) {
                timerDuration.longValue = (it.toLong() * 1000) / 2
            }
        })
        Button(
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp),
            onClick = {
                isRunning.value = true
                coroutineScope.launch {
                    while (isRunning.value && timerDuration.longValue > 0) {
                        courtIndex.intValue = 0
                        delay(timerDuration.longValue)
                        courtIndex.intValue = Random.nextInt(1, size)
                        delay(timerDuration.longValue)
                }
            }
        }
        ){
            Text(text = "Timer Start")
        }
    }
    Button(
        modifier = Modifier
            .size(width = 150.dp, height = 50.dp),
        onClick =
    {
        isRunning.value = false
        courtIndex.intValue = 0
    }
    ) {
        Text(text = "Stop Timer")
    }
}
