package com.chasev.trafflabtest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.chasev.trafflabtest.navigation.AppDestinationArgs.TITLE_ARG
import com.chasev.trafflabtest.navigation.AppDestinationArgs.TRANSACTION_ID_ARG
import com.chasev.trafflabtest.navigation.AppDestinationArgs.USER_MESSAGE_ARG
import com.chasev.trafflabtest.navigation.AppScreens.ADD_EDIT_TRANSACTION_SCREEN
import com.chasev.trafflabtest.navigation.AppScreens.STATISTICS_SCREEN
import com.chasev.trafflabtest.navigation.AppScreens.TRANSACTIONS_LIST_SCREEN

private object AppScreens {
    const val TRANSACTIONS_LIST_SCREEN = "transactionsList"
    const val ADD_EDIT_TRANSACTION_SCREEN = "addEditTransactionScreen"
    const val STATISTICS_SCREEN = "statisticScreen"
}

object AppDestinations {
    const val TRANSACTION_LIST_ROUTE =
        "$TRANSACTIONS_LIST_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val ADD_EDIT_TRANSACTION_ROUTE =
        "$ADD_EDIT_TRANSACTION_SCREEN/{$TITLE_ARG}?$TRANSACTION_ID_ARG={$TRANSACTION_ID_ARG}"
    const val STATISTICS_SCREEN_ROUTE = STATISTICS_SCREEN
}

object AppDestinationArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val TRANSACTION_ID_ARG = "transactionId"
    const val TITLE_ARG = "title"
}

class TransactionNavigationActions(private val navController: NavController) {

    fun navigateToTransactions(userMessage: Int = 0) {
        val navigatesFromDrawer = userMessage == 0
        navController.navigate(
            TRANSACTIONS_LIST_SCREEN.let {
                if (userMessage != 0) "$it?$USER_MESSAGE_ARG=$userMessage" else it
            }
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = !navigatesFromDrawer
                saveState = navigatesFromDrawer
            }
            launchSingleTop = true
            restoreState = navigatesFromDrawer
        }
    }

    fun navigateToAddEditTransaction(title: Int, transactionId: String?) {
        navController.navigate(
            "$ADD_EDIT_TRANSACTION_SCREEN/$title".let {
                if (transactionId != null) "$it?$TRANSACTION_ID_ARG=$transactionId" else it
            }
        )
    }

    fun navigateToStatisticsScreen(){
         navController.navigate(
             STATISTICS_SCREEN
         )
    }
}
