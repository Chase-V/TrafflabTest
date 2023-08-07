package com.chasev.trafflabtest.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun chartIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "pie_chart",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(21.333f, 18.667f)
                horizontalLineToRelative(12.375f)
                quadToRelative(-0.458f, -4.875f, -3.958f, -8.438f)
                quadToRelative(-3.5f, -3.562f, -8.417f, -3.937f)
                close()
                moveToRelative(-2.625f, 15.041f)
                verticalLineTo(6.292f)
                quadToRelative(-5.291f, 0.5f, -8.875f, 4.437f)
                quadTo(6.25f, 14.667f, 6.25f, 20f)
                reflectiveQuadToRelative(3.583f, 9.271f)
                quadToRelative(3.584f, 3.937f, 8.875f, 4.437f)
                close()
                moveToRelative(2.625f, 0f)
                quadToRelative(4.875f, -0.416f, 8.375f, -3.958f)
                quadToRelative(3.5f, -3.542f, 4f, -8.458f)
                horizontalLineTo(21.333f)
                close()
                moveTo(20f, 20f)
                close()
                moveToRelative(0f, 16.375f)
                quadToRelative(-3.417f, 0f, -6.396f, -1.292f)
                quadToRelative(-2.979f, -1.291f, -5.187f, -3.5f)
                quadToRelative(-2.209f, -2.208f, -3.5f, -5.187f)
                quadTo(3.625f, 23.417f, 3.625f, 20f)
                reflectiveQuadToRelative(1.292f, -6.396f)
                quadToRelative(1.291f, -2.979f, 3.5f, -5.187f)
                quadToRelative(2.208f, -2.209f, 5.187f, -3.5f)
                quadTo(16.583f, 3.625f, 20f, 3.625f)
                reflectiveQuadToRelative(6.396f, 1.292f)
                quadToRelative(2.979f, 1.291f, 5.208f, 3.5f)
                quadToRelative(2.229f, 2.208f, 3.5f, 5.187f)
                reflectiveQuadTo(36.375f, 20f)
                quadToRelative(0f, 3.417f, -1.271f, 6.396f)
                reflectiveQuadToRelative(-3.5f, 5.187f)
                quadToRelative(-2.229f, 2.209f, -5.208f, 3.5f)
                quadToRelative(-2.979f, 1.292f, -6.396f, 1.292f)
                close()
            }
        }.build()
    }
}

@Composable
fun addCircleIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "playlist_add_circle",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(25.375f, 24.625f)
                verticalLineToRelative(2.083f)
                quadToRelative(0f, 0.542f, 0.354f, 0.896f)
                reflectiveQuadToRelative(0.938f, 0.354f)
                quadToRelative(0.541f, 0f, 0.937f, -0.375f)
                reflectiveQuadToRelative(0.396f, -0.916f)
                verticalLineToRelative(-2.042f)
                horizontalLineToRelative(2.042f)
                quadToRelative(0.541f, 0f, 0.916f, -0.354f)
                reflectiveQuadToRelative(0.375f, -0.938f)
                quadToRelative(0f, -0.541f, -0.395f, -0.937f)
                quadTo(30.542f, 22f, 30f, 22f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(-2.042f)
                quadToRelative(0f, -0.541f, -0.375f, -0.916f)
                reflectiveQuadToRelative(-0.958f, -0.375f)
                quadToRelative(-0.542f, 0f, -0.917f, 0.395f)
                quadToRelative(-0.375f, 0.396f, -0.375f, 0.938f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(-2.083f)
                quadToRelative(-0.542f, 0f, -0.896f, 0.375f)
                reflectiveQuadToRelative(-0.354f, 0.958f)
                quadToRelative(0f, 0.542f, 0.375f, 0.917f)
                reflectiveQuadToRelative(0.916f, 0.375f)
                close()
                moveToRelative(-12.042f, -5f)
                horizontalLineToRelative(8.375f)
                quadToRelative(0.542f, 0f, 0.917f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.917f)
                quadToRelative(0f, -0.541f, -0.396f, -0.937f)
                reflectiveQuadTo(21.667f, 17f)
                horizontalLineToRelative(-8.375f)
                quadToRelative(-0.542f, 0f, -0.896f, 0.396f)
                reflectiveQuadToRelative(-0.354f, 0.937f)
                quadToRelative(0f, 0.542f, 0.375f, 0.917f)
                reflectiveQuadToRelative(0.916f, 0.375f)
                close()
                moveToRelative(0f, -5f)
                horizontalLineToRelative(8.375f)
                quadToRelative(0.542f, 0f, 0.917f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.917f)
                quadToRelative(0f, -0.541f, -0.396f, -0.937f)
                reflectiveQuadTo(21.667f, 12f)
                horizontalLineToRelative(-8.375f)
                quadToRelative(-0.542f, 0f, -0.896f, 0.396f)
                reflectiveQuadToRelative(-0.354f, 0.937f)
                quadToRelative(0f, 0.542f, 0.375f, 0.917f)
                reflectiveQuadToRelative(0.916f, 0.375f)
                close()
                moveTo(13.542f, 25f)
                horizontalLineToRelative(1.333f)
                quadToRelative(0.625f, 0f, 1.042f, -0.438f)
                quadToRelative(0.416f, -0.437f, 0.416f, -1.062f)
                reflectiveQuadToRelative(-0.437f, -1.062f)
                quadTo(15.458f, 22f, 14.833f, 22f)
                horizontalLineTo(13.5f)
                quadToRelative(-0.625f, 0f, -1.042f, 0.438f)
                quadToRelative(-0.416f, 0.437f, -0.416f, 1.062f)
                reflectiveQuadToRelative(0.437f, 1.062f)
                quadToRelative(0.438f, 0.438f, 1.063f, 0.438f)
                close()
                moveTo(20f, 36.375f)
                quadToRelative(-3.458f, 0f, -6.458f, -1.25f)
                reflectiveQuadToRelative(-5.209f, -3.458f)
                quadToRelative(-2.208f, -2.209f, -3.458f, -5.209f)
                quadToRelative(-1.25f, -3f, -1.25f, -6.458f)
                reflectiveQuadToRelative(1.25f, -6.437f)
                quadToRelative(1.25f, -2.98f, 3.458f, -5.188f)
                quadToRelative(2.209f, -2.208f, 5.209f, -3.479f)
                quadToRelative(3f, -1.271f, 6.458f, -1.271f)
                reflectiveQuadToRelative(6.438f, 1.271f)
                quadToRelative(2.979f, 1.271f, 5.187f, 3.479f)
                reflectiveQuadToRelative(3.479f, 5.188f)
                quadToRelative(1.271f, 2.979f, 1.271f, 6.437f)
                reflectiveQuadToRelative(-1.271f, 6.458f)
                quadToRelative(-1.271f, 3f, -3.479f, 5.209f)
                quadToRelative(-2.208f, 2.208f, -5.187f, 3.458f)
                quadToRelative(-2.98f, 1.25f, -6.438f, 1.25f)
                close()
                moveTo(20f, 20f)
                close()
                moveToRelative(0f, 13.75f)
                quadToRelative(5.833f, 0f, 9.792f, -3.958f)
                quadTo(33.75f, 25.833f, 33.75f, 20f)
                reflectiveQuadToRelative(-3.958f, -9.792f)
                quadTo(25.833f, 6.25f, 20f, 6.25f)
                reflectiveQuadToRelative(-9.792f, 3.958f)
                quadTo(6.25f, 14.167f, 6.25f, 20f)
                reflectiveQuadToRelative(3.958f, 9.792f)
                quadTo(14.167f, 33.75f, 20f, 33.75f)
                close()
            }
        }.build()
    }
}

@Preview(showBackground = true)
@Composable
fun IconsPreview() {
    Column {
        IconButton(onClick = { }) {
            Icon(imageVector = chartIcon(), contentDescription = null)
        }

        IconButton(onClick = {  }) {
            Icon(imageVector = addCircleIcon(), contentDescription = null)
        }
    }
}