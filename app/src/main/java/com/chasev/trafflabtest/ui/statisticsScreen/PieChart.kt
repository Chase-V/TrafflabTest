package com.chasev.trafflabtest.ui.statisticsScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    values: List<Float> = listOf(15f, 35f, 50f),
    colors: List<Color> = listOf(Color(0xFF58BDFF), Color(0xFF125B7F), Color(0xFF092D40)),
    legend: List<String> = listOf("Mango", "Banana", "Apple"),
    chartModifier: Modifier = Modifier
) {

    val sumOfValues = values.sum()

    val proportions = values.map {
        it * 100 / sumOfValues
    }

    val sweepAngles = proportions.map {
        it * 360 / 100
    }

    val percentage = values.map {
        "(${((it * 100) / sumOfValues)}%)"
    }

    Canvas(
        modifier = chartModifier
    ) {
        var startAngle = -90f

        for (i in sweepAngles.indices) {
            drawArc(
                color = colors[i],
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                useCenter = true
            )
            startAngle += sweepAngles[i]
        }
    }

    Spacer(modifier = Modifier.height(32.dp))

    Column {
        for (i in values.indices) {
            DisplayLegend(
                color = colors[i],
                legend = legend[i],
                values[i].toString(),
                percentage[i]
            )
        }
    }
}

@Composable
fun DisplayLegend(color: Color, legend: String, value: String, additionalInfo: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.width(16.dp),
            thickness = 4.dp,
            color = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "$legend: $value $additionalInfo",
            color = Color.Black
        )
    }
}