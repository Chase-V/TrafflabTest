package com.chasev.trafflabtest.ui.statisticsScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val dateRangePickerState = rememberDateRangePickerState()
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PieChart(
                modifier = Modifier
                    .padding(140.dp),
                values = listOf(uiState.income, uiState.expense),
                colors = listOf(Color.Green.copy(alpha = 0.3f), Color.Red.copy(alpha = 0.3f)),
                legend = listOf("Доход", "Расход"),
                chartModifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f)
            )

            Button(onClick = { coroutineScope.launch { modalBottomSheetState.expand() } }) {
                Text(text = "Выбрать промежуток")
            }

            if (modalBottomSheetState.isVisible) {
                ModalBottomSheet(
                    onDismissRequest = {
                        coroutineScope.launch {
                            modalBottomSheetState.hide()
                        }
                    },
                    sheetState = modalBottomSheetState
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        DateRangePicker(
                            state = dateRangePickerState,
                            modifier = Modifier.fillMaxHeight(0.8f)
                        )
                        Button(
                            onClick = {
                                if (dateRangePickerState.selectedStartDateMillis != null
                                    && dateRangePickerState.selectedEndDateMillis != null
                                ) {
                                    viewModel.setPickedDate(
                                        startDate = Date(dateRangePickerState.selectedStartDateMillis!!),
                                        endDate = Date(dateRangePickerState.selectedEndDateMillis!!)
                                    )
                                }
                                coroutineScope.launch { modalBottomSheetState.hide() }
                            }
                        ) {
                            Text(text = "Готово")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatisticsScreenPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PieChart(
                modifier = Modifier.fillMaxSize(),
                values = listOf(1350f, 1310f),
                colors = listOf(Color.Green.copy(alpha = 0.3f), Color.Red.copy(alpha = 0.3f)),
                legend = listOf("Доход", "Расход"),
                chartModifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
            )

        }

    }
}