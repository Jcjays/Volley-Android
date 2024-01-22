package com.example.volleysample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.volleysample.ui.theme.VolleySampleTheme
import com.example.volleysample.viewmodel.HomeState
import com.example.volleysample.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VolleySampleTheme {
                // A surface container using the 'background' color from the theme

                val homeState = homeViewModel.homeStateFlow.collectAsState()
                Home(homeState.value)
            }
        }
    }
}

@Composable
fun Home(homeState: HomeState){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Greeting(name = homeState.data?.joke ?: "No Result")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    VolleySampleTheme {
        Home(HomeState())
    }
}