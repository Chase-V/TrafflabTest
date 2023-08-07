package com.chasev.trafflabtest.ui.addEditTransaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chasev.trafflabtest.utils.dateToLocal
import java.util.Date

@Composable
fun AddEditTransactionContent(
    isLoading: Boolean,
    isSaving: Boolean,
    amount: Float,
    comment: String,
    isIncome: Boolean,
    whenAdded: Date,
    onAmountChanged: (Float) -> Unit,
    onCommentChanged: (String) -> Unit,
    onIsIncomeChanged: (Boolean) -> Unit,
    onDatePickerClick: () -> Unit
) {

    val lightGreen = Color.Green.copy(alpha = 0.3f)
    val lightRed = Color.Red.copy(0.2f)

    val numberPattern = remember { Regex("[0-9]*(\\.?[0-9]{1,2}\$)?") }

    if (isLoading) LoadingContent() else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(text = "Расход")
                Switch(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    checked = isIncome,
                    onCheckedChange = onIsIncomeChanged,
                    thumbContent = {
                        Icon(
                            imageVector = if (isIncome) Icons.Filled.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = lightGreen,
                        checkedBorderColor = lightGreen,
                        checkedTrackColor = lightGreen.copy(0.05f),
                        uncheckedBorderColor = lightRed,
                        uncheckedThumbColor = lightRed,
                        uncheckedTrackColor = lightRed.copy(0.05f)
                    ),
                )
                Text(text = "Доход")
            }

            OutlinedTextField(
                value = amount.toString(),
                onValueChange = {
                    if (it.matches(numberPattern)) {
                        try {
                            it.toFloat().run { onAmountChanged(this) }
                        } catch (_: NumberFormatException) {
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(1f),
                leadingIcon = { if (isIncome) Text(text = "+") else Text(text = "-") },
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = comment,
                onValueChange = onCommentChanged,
                modifier = Modifier.fillMaxWidth(1f),
                maxLines = 1
            )

            Text(text = dateToLocal(whenAdded))

            TextButton(onClick = onDatePickerClick) {
                Text(text = "Выбрать дату")
            }

            if (isSaving) {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.DarkGray)
    }
}

@Preview(showBackground = true)
@Composable
fun AddEditTransactionContentPreview() {
    AddEditTransactionContent(
        isLoading = false,
        isSaving = true,
        amount = 1000f,
        comment = "asdasdasdas",
        isIncome = true,
        whenAdded = Date(),
        onAmountChanged = {},
        onCommentChanged = {},
        onIsIncomeChanged = {},
        onDatePickerClick = {}
    )
}