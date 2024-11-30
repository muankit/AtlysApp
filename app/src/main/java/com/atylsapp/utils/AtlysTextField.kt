package com.atylsapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AtlysTextField(
    text: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    leadingComposable: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                color = Color.LightGray,
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 12.dp),
            value = text,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            onValueChange = {
                onValueChange(it)
            },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    leadingComposable?.invoke()

                    Spacer(modifier = Modifier.width(4.dp))

                    Box(modifier = Modifier.weight(1f)) {
                        if (text.isEmpty()) {
                            Text(
                                modifier = Modifier,
                                text = hint,
                                color = Color.LightGray,
                                maxLines = 1,
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp
                                ),
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        innerTextField()
                    }
                }
            },
            singleLine = true,
        )
    }
}