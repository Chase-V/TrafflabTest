package com.chasev.trafflabtest.navigation

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chasev.trafflabtest.R
import com.chasev.trafflabtest.navigation.AppDestinationArgs.TITLE_ARG
import com.chasev.trafflabtest.navigation.AppDestinationArgs.TRANSACTION_ID_ARG
import com.chasev.trafflabtest.navigation.AppDestinationArgs.USER_MESSAGE_ARG
import com.chasev.trafflabtest.ui.addEditTransaction.AddEditTransactionScreen
import com.chasev.trafflabtest.ui.statisticsScreen.StatisticsScreen
import com.chasev.trafflabtest.ui.transactionsList.TransactionsListScreen
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = AppDestinations.TRANSACTION_LIST_ROUTE,
    navActions: TransactionNavigationActions = remember(navController) {
        TransactionNavigationActions(navController = navController)
    }
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            AppDestinations.TRANSACTION_LIST_ROUTE,
            arguments = listOf(
                navArgument(USER_MESSAGE_ARG) { type = NavType.IntType; defaultValue = 0 },
            )
        ) {
            TransactionsListScreen(
                addTransaction = {
                    navActions.navigateToAddEditTransaction(
                        R.string.add_transaction,
                        null
                    )
                },
                editTransaction = { transactionId ->
                    navActions.navigateToAddEditTransaction(
                        R.string.edit_transaction,
                        transactionId
                    )
                },
                openStatistics = {
                    navActions.navigateToStatisticsScreen()
                }

            )
        }

        composable(
            AppDestinations.ADD_EDIT_TRANSACTION_ROUTE,
            arguments = listOf(
                navArgument(TITLE_ARG) { type = NavType.IntType },
                navArgument(TRANSACTION_ID_ARG) { type = NavType.StringType; nullable = true }
            )
        ) { entry ->
            val transactionId = entry.arguments?.getString(TRANSACTION_ID_ARG)
            AddEditTransactionScreen(
                onTransactionUpdate = {
                    navActions.navigateToTransactions(
                        if (transactionId == null) ADD_EDIT_RESULT_OK else EDIT_RESULT_OK
                    )
                }
            )

        }

        composable(
            AppDestinations.STATISTICS_SCREEN_ROUTE,
        ){
            StatisticsScreen()
        }

    }
}

const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3