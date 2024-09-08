package com.example.calculator
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import com.example.calculator.ui.theme.PurpleGrey80


val buttonList = listOf(
    "C","AC","%","/",
    "9","8","7","*",
    "6","5","4","+",
    "1","2","3","-",
    "00","0",".","=",
)
@Composable
fun  Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel){
    val equationText = viewModel.equationtext.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Box(modifier = modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        )
        {
            Text(
                text = equationText.value?:"",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = resultText.value?:"",
                style = TextStyle(
                    fontSize = 60.sp,
                            textAlign = TextAlign.End
                ),
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(10.dp))
           LazyVerticalGrid(
               columns = GridCells.Fixed(4),
               ) {
               items(buttonList){
                   CalculatorButton(btn = it, onClick={
                       viewModel.onButtonClick(it)
                   })
               }
           }
        }
    }
}
@Composable
fun CalculatorButton(btn : String, onClick:()-> Unit) {
   Box(modifier = Modifier.padding(10.dp)){
      FloatingActionButton(
          onClick = onClick,
          modifier = Modifier.size(100.dp),
          shape = RectangleShape,
          containerColor = Color.DarkGray,
          contentColor = Color.White,
          ) {
          Text(text = btn, fontSize = 30.sp, fontWeight = FontWeight.Bold)
      }
       }
   }

