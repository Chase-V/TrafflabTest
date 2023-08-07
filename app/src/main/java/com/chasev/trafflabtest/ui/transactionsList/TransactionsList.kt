package com.chasev.trafflabtest.ui.transactionsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chasev.trafflabtest.R
import com.chasev.trafflabtest.data.repository.BudgetTransaction
import com.chasev.trafflabtest.utils.dateToLocal
import java.util.Date

@Composable
fun TransactionsList(
    transactions: List<BudgetTransaction>,
    onEditClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
) {

    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(transactions) { item ->
            TransactionListItem(
                transaction = item,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TransactionListItem(
    transaction: BudgetTransaction,
    onEditClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { transaction.transactionId?.let { onEditClick(it) } },
        colors = CardDefaults.cardColors(
            containerColor = if (transaction.isIncome)
                Color.Green.copy(alpha = 0.15f)
            else Color.Red.copy(alpha = 0.15f)
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(text = transaction.comment.orEmpty(), modifier = Modifier.weight(1f))
                Text(
                    text = if (transaction.isIncome) {
                        "+" + transaction.amount.toString()
                    } else {
                        "-" + transaction.amount.toString()
                    },
                    modifier = Modifier,
                    textAlign = TextAlign.End,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = dateToLocal(transaction.whenAdded),
                    textAlign = TextAlign.End,
                    color = Color.Black.copy(alpha = 0.5f)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.deleteTransaction),
                    modifier = Modifier
                        .widthIn(min = 24.dp)
                        .defaultMinSize(24.dp)
                        .clickable { transaction.transactionId?.let { onDeleteClick(it) } },
                    tint = Color(0xAAFF6D6B)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    TransactionListItem(
        transaction = BudgetTransaction(
            comment = "asdas asdqwqersdfkml zxcvkml asdfkl asda sd  qasd asd asd asd asd qwe as d",
            isIncome = true,
            amount = 1000f,
            whenAdded = Date()
        ),
        onEditClick = {},
        onDeleteClick = {}
    )
}